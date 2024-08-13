package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.UnexpectedAfterData;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;

public class ExtractMethodCommitMessage implements CommitMessage {
    private final Refactoring refactoring;

    public ExtractMethodCommitMessage(Refactoring refactoring) {
        this.refactoring = refactoring;
    }

    private String getToMethodName() {
        PsiElement after = refactoring.getAfter();
        if (after instanceof PsiMethod psiMethod) {
            return psiMethod.getName();
        }
        throw new UnexpectedAfterData(after, PsiMethod.class);
    }

    @Override
    public String getMessage() {
        return "Extract method to '" + getToMethodName() + "'";
    }
}
