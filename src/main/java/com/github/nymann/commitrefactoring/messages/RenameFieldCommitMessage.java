package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;

public class RenameFieldCommitMessage implements CommitMessage {
    private final Refactoring refactoring;

    public RenameFieldCommitMessage(Refactoring refactoring) {
        this.refactoring = refactoring;
    }

    private String getNewName() {
        PsiElement after = refactoring.getAfter();
        if (after instanceof PsiField psiField) {
            return psiField.getName();
        }
        throw new RuntimeException("after was not an instance of PsiField, it was: " + after.getClass().getName());
    }

    private String getOldName() {
        PsiElement before = refactoring.getBefore();
        if (before instanceof PsiField psiField) {
            return psiField.getName();
        }
        throw new RuntimeException("after was not an instance of PsiField, it was: " + before.getClass().getName());
    }

    @Override
    public String getMessage() {
        return "Rename field '" + getOldName() + "' to '" + getNewName() + "'";
    }
}
