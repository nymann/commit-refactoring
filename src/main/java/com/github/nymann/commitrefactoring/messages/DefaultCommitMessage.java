package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;

public class DefaultCommitMessage implements CommitMessage {
    private static final Logger log = Logger.getInstance(DefaultCommitMessage.class);
    private final Refactoring refactoring;

    public DefaultCommitMessage(Refactoring refactoring) {
        this.refactoring = refactoring;
    }

    @Override
    public String getMessage() {
        log.info("Refactoring without concrete implementation, using default. RefactoringId: " + refactoring.getRefactoringId());
        logElement(refactoring.getFirstBefore(), "Before");
        logElement(refactoring.getFirstAfter(), "After");
        return refactoring.getRefactoringId().replace("refactoring.", "").replace(".", " ");
    }

    private void logElement(PsiElement element, String name) {
        if (element != null) {
            log.info("- " + name + " Element Type: " + element.getClass().getName());
            log.debug("- " + name + " Element Text: " + element.getText());
            log.debug("- " + name + " Element Language: " + element.getLanguage());
        }
    }
}
