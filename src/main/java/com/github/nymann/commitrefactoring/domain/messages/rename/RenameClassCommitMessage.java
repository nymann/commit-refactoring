package com.github.nymann.commitrefactoring.domain.messages.rename;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class RenameClassCommitMessage implements CommitMessage {

    private final String fromName;
    private final String toName;

    public RenameClassCommitMessage(String fromName, String toName) {
        this.fromName = fromName;
        this.toName = toName;
    }

    @Override
    public String getMessage() {
        return "Rename class '" + fromName + "' to '" + toName + "'";
    }
}
