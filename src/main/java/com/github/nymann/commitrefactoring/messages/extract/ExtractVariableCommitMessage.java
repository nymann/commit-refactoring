package com.github.nymann.commitrefactoring.messages.extract;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiVariable;

public class ExtractVariableCommitMessage implements CommitMessage {
    private final PsiVariable after;

    public ExtractVariableCommitMessage(PsiVariable after) {
        this.after = after;
    }

    @Override
    public String getMessage() {
        return "Extract variable '" + after.getName() + "'";
    }
}
