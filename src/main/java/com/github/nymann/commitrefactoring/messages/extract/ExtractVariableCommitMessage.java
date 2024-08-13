package com.github.nymann.commitrefactoring.messages.extract;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiLocalVariable;

public class ExtractVariableCommitMessage implements CommitMessage {
    private final PsiLocalVariable after;

    public ExtractVariableCommitMessage(PsiLocalVariable after) {
        this.after = after;
    }

    @Override
    public String getMessage() {
        return "Extract variable '" + after.getName() + "'";
    }
}
