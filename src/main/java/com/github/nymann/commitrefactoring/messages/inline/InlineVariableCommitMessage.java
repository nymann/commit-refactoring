package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CommitMessage;

public class InlineVariableCommitMessage implements CommitMessage {

    private final String variableName;

    public InlineVariableCommitMessage(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public String getMessage() {
        return "Inline variable '" + variableName + "'";
    }
}
