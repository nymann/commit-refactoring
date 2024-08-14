package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CommitMessage;

public class RenameFieldCommitMessage implements CommitMessage {
    private final String fromName;
    private final String toName;

    public RenameFieldCommitMessage(String fromName, String toName) {
        this.fromName = fromName;
        this.toName = toName;
    }

    @Override
    public String getMessage() {
        return "Rename field '" + fromName + "' to '" + toName + "'";
    }
}
