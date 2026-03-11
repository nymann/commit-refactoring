package com.github.nymann.commitrefactoring.domain.messages.extract;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

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
