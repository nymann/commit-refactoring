package com.github.nymann.commitrefactoring.domain.messages.inline;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

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
