package com.github.nymann.commitrefactoring.domain.messages.pullmembersup;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class UnknownPullMembersUpCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Pull members up";
    }
}
