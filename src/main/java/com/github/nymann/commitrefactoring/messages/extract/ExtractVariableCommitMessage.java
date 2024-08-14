package com.github.nymann.commitrefactoring.messages.extract;

import com.github.nymann.commitrefactoring.CommitMessage;

public class ExtractVariableCommitMessage implements CommitMessage {
    private final String variableName;

    public ExtractVariableCommitMessage(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public String getMessage() {
        return "Extract variable '" + variableName + "'";
    }
}
