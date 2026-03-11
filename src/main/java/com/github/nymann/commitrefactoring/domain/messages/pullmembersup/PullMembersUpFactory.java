package com.github.nymann.commitrefactoring.domain.messages.pullmembersup;

import com.github.nymann.commitrefactoring.domain.CodeElement;
import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class PullMembersUpFactory {
    public static CommitMessage create(CodeElement after) {
        if (CodeElementType.CLASS.equals(after.type())) {
            return new PullMembersUpCommitMessage(after.name());
        }
        return new UnknownPullMembersUpCommitMessage();
    }
}
