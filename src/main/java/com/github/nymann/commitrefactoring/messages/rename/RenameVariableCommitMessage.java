package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CommitMessage;

public class RenameVariableCommitMessage implements CommitMessage {

    private final String fromName;
    private final String toName;

    public RenameVariableCommitMessage(String fromName, String toName) {
        this.fromName = fromName;
        this.toName = toName;
    }

    @Override
    public String getMessage() {
        return "Rename variable '" + fromName + "' to '" + toName + "'";
    }
}
