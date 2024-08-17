package com.github.nymann.commitrefactoring.messages.pushmembersdown;

import com.github.nymann.commitrefactoring.CommitMessage;

public class PushMembersDownMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Push members down";
    }
}
