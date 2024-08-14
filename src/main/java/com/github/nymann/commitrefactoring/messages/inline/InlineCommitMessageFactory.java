package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CommitMessage;

public class InlineCommitMessageFactory {

    public static CommitMessage create(CodeElement before) {
        return switch (before.getType()) {
            case METHOD -> new InlineMethodCommitMessage(before.getName());
            case LOCAL_VARIABLE -> new InlineVariableCommitMessage(before.getName());
            default -> new DefaultInlineCommitMessage(before.getType());
        };
    }

}
