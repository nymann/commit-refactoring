package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.UnexpectedAfterData;
import com.github.nymann.commitrefactoring.UnexpectedBeforeData;
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
        throw new UnexpectedAfterData(after, PsiClass.class);
    }

    private String getOldName() {
        PsiElement before = refactoring.getBefore();
        if (before instanceof PsiClass psiClass) {
            return psiClass.getName();
        }
        throw new UnexpectedBeforeData(before, PsiClass.class);
    }

    @Override
    public String getMessage() {
        return "Rename class '" + getOldName() + "' to '" + getNewName() + "'";
    }
}
