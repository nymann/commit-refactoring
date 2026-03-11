package com.github.nymann.commitrefactoring.domain.messages.move;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class MoveMembersCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Move members";
    }
}
