package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;

public class RenameClassCommitMessage implements CommitMessage {
    private final Refactoring refactoring;

    public RenameClassCommitMessage(Refactoring refactoring) {
        this.refactoring = refactoring;
    }

    private String getNewName() {
        PsiElement after = refactoring.getAfter();
        if (after instanceof PsiClass psiClass) {
            return psiClass.getName();
        }
        throw new RuntimeException("after was not an instance of PsiClass, it was: " + after.getClass().getName());
    }

    private String getOldName() {
        PsiElement before = refactoring.getBefore();
        if (before instanceof PsiClass psiClass) {
            return psiClass.getName();
        }
        throw new RuntimeException("after was not an instance of PsiClass, it was: " + before.getClass().getName());
    }

    @Override
    public String getMessage() {
        return "Rename class '" + getOldName() + "' to '" + getNewName() + "'";
    }
}
