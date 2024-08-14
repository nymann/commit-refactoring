package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CommitMessage;

public class RenameMethodCommitMessage implements CommitMessage {

    private final String beforeName;
    private final String afterName;

    public RenameMethodCommitMessage(String beforeName, String afterName) {
        this.beforeName = beforeName;
        this.afterName = afterName;
    }

    @Override
    public String getMessage() {
        return "Rename method '" + beforeName + "' to '" + afterName + "'";
    }
}
