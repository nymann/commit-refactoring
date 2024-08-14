package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CommitMessage;

public class SafeDeleteMethodCommitMessage implements CommitMessage {

    private final String methodName;

    public SafeDeleteMethodCommitMessage(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String getMessage() {
        return "Remove unused method '" + methodName + "'";
    }
}
