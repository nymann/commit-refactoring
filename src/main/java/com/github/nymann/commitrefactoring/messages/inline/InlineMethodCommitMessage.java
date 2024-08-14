package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CommitMessage;

public class InlineMethodCommitMessage implements CommitMessage {
    private final String methodName;

    public InlineMethodCommitMessage(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String getMessage() {
        return "Inline method '" + methodName + "'";
    }
}
