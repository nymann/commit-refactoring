package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiMethod;

public class InlineMethodCommitMessage implements CommitMessage {
    private final PsiMethod method;

    public InlineMethodCommitMessage(PsiMethod before) {
        this.method = before;
    }

    @Override
    public String getMessage() {
        return "Inline method '" + method.getName() + "'";
    }
}
