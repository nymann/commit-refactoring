package com.github.nymann.commitrefactoring.domain.messages.safedelete;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class SafeDeleteParameterCommitMessage implements CommitMessage {

    private final String parameterName;

    public SafeDeleteParameterCommitMessage(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public String getMessage() {
        return "Remove unused parameter '" + parameterName + "'";
    }
}
