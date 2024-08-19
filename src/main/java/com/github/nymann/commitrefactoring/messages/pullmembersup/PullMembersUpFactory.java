package com.github.nymann.commitrefactoring.messages.pullmembersup;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import org.jetbrains.annotations.NotNull;

public class PullMembersUpFactory {
    public static @NotNull CommitMessage create(CodeElement after) {
        if (CodeElementType.CLASS.equals(after.type())) {
            return new PullMembersUpCommitMessage(after.name());
        }
        return new UnknownPullMembersUpCommitMessage();
    }
}
