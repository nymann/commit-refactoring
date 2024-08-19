package com.github.nymann.commitrefactoring.messages.pullmembersup;

import com.github.nymann.commitrefactoring.CommitMessage;

public class UnknownPullMembersUpCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Pull members up";
    }
}
