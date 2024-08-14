package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiParameter;

public class SafeDeleteParameterCommitMessage implements CommitMessage {
    private final PsiParameter removedParameter;

    public SafeDeleteParameterCommitMessage(PsiParameter removedParameter) {
        this.removedParameter = removedParameter;
    }

    @Override
    public String getMessage() {
        return "Remove unused parameter '" + removedParameter.getName() + "'";
    }
}
