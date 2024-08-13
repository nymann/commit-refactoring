package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.UnexpectedAfterData;
import com.github.nymann.commitrefactoring.UnexpectedBeforeData;
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
        throw new UnexpectedAfterData(after, PsiField.class);
    }

    private String getOldName() {
        PsiElement before = refactoring.getBefore();
        if (before instanceof PsiField psiField) {
            return psiField.getName();
        }
        throw new UnexpectedBeforeData(before, PsiField.class);
    }

    @Override
    public String getMessage() {
        return "Rename field '" + getOldName() + "' to '" + getNewName() + "'";
    }
}
