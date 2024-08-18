package com.github.nymann.commitrefactoring.messages.extractdelegate;

import com.github.nymann.commitrefactoring.CommitMessage;

public class ExtractDelegateClassCommitMessage implements CommitMessage {
    private final String fromClassName;
    private final String toClassName;

    public ExtractDelegateClassCommitMessage(String fromClassName, String toClassName) {
        this.fromClassName = fromClassName;
        this.toClassName = toClassName;
    }

    @Override
    public String getMessage() {
        return "Extract delegate from '" + fromClassName + "' to '" + toClassName + "'";
    }
}
