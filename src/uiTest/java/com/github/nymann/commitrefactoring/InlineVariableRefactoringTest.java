package com.github.nymann.commitrefactoring;

import com.intellij.remoterobot.RemoteRobot;
import com.intellij.remoterobot.fixtures.ComponentFixture;
import com.intellij.remoterobot.fixtures.JCheckboxFixture;
import com.intellij.remoterobot.fixtures.dataExtractor.RemoteText;
import com.intellij.remoterobot.stepsProcessing.StepWorkerKt;
import com.intellij.remoterobot.utils.Keyboard;
import junit.framework.TestCase;

import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.intellij.remoterobot.search.locators.Locators.byXpath;

public class InlineVariableRefactoringTest extends TestCase {
    private final RemoteRobot remoteRobot = new RemoteRobot("http://127.0.0.1:8082");
    private final Keyboard keyboard = new Keyboard(remoteRobot);
    private final Duration timeout = Duration.ofSeconds(30);

    public void testInlineVariableRefactoringAndCommitMessage() {
        StepWorkerKt.step("Creating a new project", () -> createNewProject(generateProjectName()));
        StepWorkerKt.step("Creating and opening a new file", () -> this.createAndOpenFile("A"));
        safeDeleteNext();
        StepWorkerKt.step("Check that commit message is \"Remove unused class 'A'\"", () -> this.assertCommitMessageAndCommit("A"));
        StepWorkerKt.step("Creating and opening a new file", () -> this.createAndOpenFile("A"));
        StepWorkerKt.step("Creating and opening a new file", () -> this.createAndOpenFile("B"));
        safeDeleteNext();
        StepWorkerKt.step("Check that commit message is \"Remove unused class 'B'\"", () -> this.assertCommitMessageAndCommit("B"));
    }

    private String generateProjectName() {
        String prefix = "test";
        StringBuilder projectName = new StringBuilder(prefix);
        Random random = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < 4; i++) {
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));
            projectName.append(randomChar);
        }

        return projectName.toString();
    }

    private void switchToCommitDialog() {
        remoteRobot.find(ComponentFixture.class, byXpath("//div[@tooltiptext='Commit']"), Duration.ofSeconds(10)).click();
        ComponentFixture componentFixture = remoteRobot.find(ComponentFixture.class, byXpath("//div[@tooltiptext='Options']"), timeout);
        componentFixture.click();
        keyboard.key(KeyEvent.VK_DOWN, Duration.ofSeconds(1));
        keyboard.key(KeyEvent.VK_ENTER, Duration.ofSeconds(1));
    }

    private void createNewProject(String projectName) {
        System.out.println("Break cache");
        remoteRobot.find(ComponentFixture.class, byXpath("//div[@defaulticon='createNewProjectTab.svg' or (@accessiblename='New Project' and @class='JBOptionButton' and @text='New Project')]"), timeout).click();
        ComponentFixture componentFixture = remoteRobot.find(ComponentFixture.class, byXpath("//div[@accessiblename='Name:' and @class='JBTextField']"), timeout);
        selectAllText(componentFixture);
        keyboard.enterText(projectName);
        ComponentFixture git = remoteRobot.find(ComponentFixture.class, byXpath("//div[@text='Create Git repository']"), timeout);
        if (!isCheckboxChecked()) {
            git.click();
        }
        remoteRobot.find(ComponentFixture.class, byXpath("//div[@text='Create']"), timeout).click();
        remoteRobot.find(ComponentFixture.class, byXpath("//div[@visible_text='" + projectName + "']"), timeout);
    }

    private boolean isCheckboxChecked() {
        JCheckboxFixture checkbox = remoteRobot.find(JCheckboxFixture.class, JCheckboxFixture.byText("Create Git repository"), Duration.ofSeconds(5));
        return checkbox.isSelected();
    }


    private void createAndOpenFile(String className) {
        ComponentFixture src = remoteRobot.find(ComponentFixture.class, byXpath("//div[@visible_text='src']"), timeout);
        src.rightClick();
        ComponentFixture newB = remoteRobot.find(ComponentFixture.class, byXpath("//div[@text='New']"));
        newB.click();
        ComponentFixture javaClass = remoteRobot.find(ComponentFixture.class, byXpath("//div[@text='New']//div[@text='Java Class']"));
        javaClass.click();
        keyboard.enterText(className);
        keyboard.enter();
        keyboard.enter();
        remoteRobot.find(ComponentFixture.class, byXpath("//div[@accessiblename='Editor for " + className + ".java']"), timeout).click();
    }

    private void selectAllText(ComponentFixture componentFixture) {
        componentFixture.click();
        Keyboard keyboard = new Keyboard(remoteRobot);
        keyboard.hotKey(KeyEvent.VK_CONTROL, KeyEvent.VK_A);  // For Windows/Linux, use Ctrl + A to select all
        keyboard.hotKey(KeyEvent.VK_META, KeyEvent.VK_A);     // For macOS, use Command + A to select all
    }

    private void safeDeleteNext() {
        keyboard.key(KeyEvent.VK_F2, Duration.ofSeconds(1));
        keyboard.hotKey(KeyEvent.VK_ALT, KeyEvent.VK_ENTER);
        keyboard.key(KeyEvent.VK_ENTER, Duration.ofSeconds(1));
        keyboard.key(KeyEvent.VK_ENTER, Duration.ofSeconds(1));
        keyboard.hotKey(KeyEvent.VK_META, KeyEvent.VK_K);
    }

    private void assertCommitMessageAndCommit(String className) {
        assertCommitMessage(className);
        remoteRobot.find(ComponentFixture.class, byXpath("//div[@class='MainButton']"), Duration.ofSeconds(10)).click();
    }

    private void assertCommitMessage(String className) {
        List<String> text = new ArrayList<>();
        ComponentFixture editorComponent = remoteRobot.find(ComponentFixture.class, byXpath(".//div[@accessiblename='Editor']"));
        for (RemoteText remoteText : editorComponent.getData().getAll()) {
            text.add(remoteText.getText());
        }
        String actual = String.join("", text);
        assertEquals("Remove unused class '" + className + "'", actual);
    }
}
