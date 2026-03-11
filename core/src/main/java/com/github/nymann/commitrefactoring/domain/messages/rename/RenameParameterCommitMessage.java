package com.github.nymann.commitrefactoring.domain.messages.rename;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class RenameParameterCommitMessage implements CommitMessage {

    private final String fromName;
    private final String toName;

    public RenameParameterCommitMessage(String fromName, String toName) {
        this.fromName = fromName;
        this.toName = toName;
    }

    @Override
    public String getMessage() {
        return "Rename parameter '" + fromName + "' to '" + toName + "'";
    }
}
