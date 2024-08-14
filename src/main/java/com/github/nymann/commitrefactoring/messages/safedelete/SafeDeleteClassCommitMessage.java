package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CommitMessage;

public class SafeDeleteClassCommitMessage implements CommitMessage {
    private final String className;

    public SafeDeleteClassCommitMessage(String className) {
        this.className = className;
    }

    @Override
    public String getMessage() {
        return "Remove unused class '" + className + "'";
    }
}
