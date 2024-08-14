package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CommitMessage;

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
