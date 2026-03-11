package com.github.nymann.commitrefactoring.adapter.in.intellij;

import com.github.nymann.commitrefactoring.adapter.out.intellij.CommitRefactoringSettings;
import com.intellij.openapi.options.Configurable;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.FlowLayout;

public class CommitRefactoringSettingsUi implements Configurable {
    private JPanel panel;
    private JTextField templateField;
    private JTextField defaultCommitMessage;
    private JBTextArea textToAppendToCommit;
    private JCheckBox commitMessageViaButtonOnly;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Commit Refactoring";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        templateField = new JTextField();
        defaultCommitMessage = new JTextField();
        textToAppendToCommit = new JBTextArea(5, 20);
        textToAppendToCommit.setLineWrap(true);
        textToAppendToCommit.setWrapStyleWord(true);
        commitMessageViaButtonOnly = new JCheckBox();

        panel = FormBuilder.createFormBuilder()
                .addComponent(resetButtonRow())
                .addLabeledComponent("Template", templateField)
                .addLabeledComponent("Default message", defaultCommitMessage)
                .addLabeledComponent("Set commit message via button only", commitMessageViaButtonOnly)
                .addComponent(new JBLabel("Text to append to commit"), 10)
                .addComponentFillVertically(new JBScrollPane(textToAppendToCommit), 1)
                .getPanel();

        reset();
        return panel;
    }

    private @NotNull JPanel resetButtonRow() {
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetToDefault());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(resetButton);
        return buttonPanel;
    }

    public void resetToDefault() {
        templateField.setText("${refactoring}");
        defaultCommitMessage.setText("UNSAFE");
        commitMessageViaButtonOnly.setSelected(false);
    }

    @Override
    public boolean isModified() {
        CommitRefactoringSettings settings = CommitRefactoringSettings.getInstance();
        boolean templateHasChanged = !templateField.getText().equals(settings.getTemplate());
        boolean defaultCommitMessageHasChanged = !defaultCommitMessage.getText().equals(settings.getDefaultCommitMessage());
        boolean textToAppendToCommitHasChanged = !textToAppendToCommit.getText().equals(settings.getTextToAppendToCommit());
        boolean commitMessageViaButtonOnlyHasChanged = commitMessageViaButtonOnly.isSelected() != settings.getCommitMessageViaButtonOnly();
        return templateHasChanged || defaultCommitMessageHasChanged || commitMessageViaButtonOnlyHasChanged || textToAppendToCommitHasChanged;
    }

    @Override
    public void apply() {
        CommitRefactoringSettings instance = CommitRefactoringSettings.getInstance();
        instance.setTemplate(templateField.getText());
        instance.setDefaultCommitMessage(defaultCommitMessage.getText());
        instance.setCommitMessageViaButtonOnly(commitMessageViaButtonOnly.isSelected());
        instance.setTextToAppendToCommit(textToAppendToCommit.getText());
    }

    @Override
    public void reset() {
        CommitRefactoringSettings settings = CommitRefactoringSettings.getInstance();
        templateField.setText(settings.getTemplate());
        defaultCommitMessage.setText(settings.getDefaultCommitMessage());
        commitMessageViaButtonOnly.setSelected(settings.getCommitMessageViaButtonOnly());
        textToAppendToCommit.setText(settings.getTextToAppendToCommit());
    }

    @Override
    public void disposeUIResources() {
        panel = null;
        templateField = null;
        defaultCommitMessage = null;
        commitMessageViaButtonOnly = null;
        textToAppendToCommit = null;
    }
}
