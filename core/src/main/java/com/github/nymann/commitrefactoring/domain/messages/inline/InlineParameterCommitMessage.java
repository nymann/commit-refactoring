package com.github.nymann.commitrefactoring.domain.messages.inline;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class InlineParameterCommitMessage implements CommitMessage {
    private final String parameterName;

    public InlineParameterCommitMessage(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public String getMessage() {
        return "Inline parameter '" + parameterName + "'";
    }
}
