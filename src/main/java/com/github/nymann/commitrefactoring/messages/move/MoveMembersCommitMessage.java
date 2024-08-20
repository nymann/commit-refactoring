package com.github.nymann.commitrefactoring.messages.move;

import com.github.nymann.commitrefactoring.CommitMessage;

public class MoveMembersCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Move members";
    }
}
