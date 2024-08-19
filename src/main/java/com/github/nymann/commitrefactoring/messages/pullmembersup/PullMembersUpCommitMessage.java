package com.github.nymann.commitrefactoring.messages.pullmembersup;

import com.github.nymann.commitrefactoring.CommitMessage;

public class PullMembersUpCommitMessage implements CommitMessage {
    private final String className;

    public PullMembersUpCommitMessage(String className) {
        this.className = className;
    }

    @Override
    public String getMessage() {
        return "Pull members up to '" + className + "'";
    }
}
