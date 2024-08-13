package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.UnexpectedAfterData;
import com.github.nymann.commitrefactoring.UnexpectedBeforeData;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;

public class RenameMethodCommitMessage implements CommitMessage {
    private final Refactoring refactoring;

    public RenameMethodCommitMessage(Refactoring refactoring) {
        this.refactoring = refactoring;
    }

    private String getNewName() {
        PsiElement after = refactoring.getAfter();
        if (after instanceof PsiMethod psiMethod) {
            return psiMethod.getName();
        }
        throw new UnexpectedAfterData(after, PsiMethod.class);
    }

    private String getOldName() {
        PsiElement before = refactoring.getBefore();
        if (before instanceof PsiMethod psiMethod) {
            return psiMethod.getName();
        }
        throw new UnexpectedBeforeData(before, PsiMethod.class);
    }

    @Override
    public String getMessage() {
        return "Rename method '" + getOldName() + "' to '" + getNewName() + "'";
    }
}
