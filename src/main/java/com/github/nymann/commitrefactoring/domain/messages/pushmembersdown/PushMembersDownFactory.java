package com.github.nymann.commitrefactoring.domain.messages.pushmembersdown;

import com.github.nymann.commitrefactoring.domain.*;

public class PushMembersDownFactory {
    public static CommitMessage create(CodeElement before, CodeElement after) {
        if(CodeElementType.CLASS.equals(before.type()) && CodeElementType.CLASS.equals(after.type())) {
            return new PushClassMembersDownMessage(before.name(),after.name());
        }
        return new PushMembersDownMessage();
    }
}
