package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiVariable;

public class RenameVariableCommitMessage implements CommitMessage {
    private final Refactoring refactoring;

    public RenameVariableCommitMessage(Refactoring refactoring) {
        this.refactoring = refactoring;
    }

    private String getNewName() {
        PsiElement after = refactoring.getAfter();
        if (after instanceof PsiVariable psiVariable) {
            return psiVariable.getName();
        }
        throw new RuntimeException("after was not an instance of PsiVariable, it was: " + after.getClass().getName());
    }

    private String getOldName() {
        PsiElement before = refactoring.getBefore();
        if (before instanceof PsiVariable psiVariable) {
            return psiVariable.getName();
        }
        throw new RuntimeException("after was not an instance of PsiVariable, it was: " + before.getClass().getName());
    }

    @Override
    public String getMessage() {
        return "Rename variable '" + getOldName() + "' to '" + getNewName() + "'";
    }
}
