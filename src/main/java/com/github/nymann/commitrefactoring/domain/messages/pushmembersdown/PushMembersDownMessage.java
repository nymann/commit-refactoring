package com.github.nymann.commitrefactoring.domain.messages.pushmembersdown;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class PushMembersDownMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Push members down";
    }
}
