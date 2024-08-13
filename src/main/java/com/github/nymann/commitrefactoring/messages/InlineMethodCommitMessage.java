package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.UnexpectedBeforeData;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;

public class InlineMethodCommitMessage implements CommitMessage {
    private final Refactoring refactoring;

    public InlineMethodCommitMessage(Refactoring refactoring) {
        this.refactoring = refactoring;
    }

    private String getFromMethodName() {
        PsiElement before = refactoring.getBefore();
        if (before instanceof PsiMethod psiMethod) {
            return psiMethod.getName();
        }
        throw new UnexpectedBeforeData(before, PsiMethod.class);
    }

    @Override
    public String getMessage() {
        return "Inline method '" + getFromMethodName() + "'";
    }
}
