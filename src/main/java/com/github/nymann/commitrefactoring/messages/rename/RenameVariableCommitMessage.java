package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.exceptions.UnexpectedAfterData;
import com.github.nymann.commitrefactoring.exceptions.UnexpectedBeforeData;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiVariable;

public class RenameVariableCommitMessage implements CommitMessage {
    private final Refactoring refactoring;

    public RenameVariableCommitMessage(Refactoring refactoring) {
        this.refactoring = refactoring;
    }

    private String getNewName() {
        PsiElement after = refactoring.getFirstAfter();
        if (after instanceof PsiVariable psiVariable) {
            return psiVariable.getName();
        }
        throw new UnexpectedAfterData(after, PsiVariable.class);
    }

    private String getOldName() {
        PsiElement before = refactoring.getFirstBefore();
        if (before instanceof PsiVariable psiVariable) {
            return psiVariable.getName();
        }
        throw new UnexpectedBeforeData(before, PsiVariable.class);
    }

    @Override
    public String getMessage() {
        return "Rename variable '" + getOldName() + "' to '" + getNewName() + "'";
    }
}
