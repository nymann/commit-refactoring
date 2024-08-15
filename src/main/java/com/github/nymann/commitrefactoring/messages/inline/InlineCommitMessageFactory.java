package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CommitMessage;

public class InlineCommitMessageFactory {

    public static CommitMessage create(CodeElement before) {
        return switch (before.type()) {
            case METHOD -> new InlineMethodCommitMessage(before.name());
            case LOCAL_VARIABLE -> new InlineVariableCommitMessage(before.name());
            case CONSTRUCTOR -> new InlineConstructorCommitMessage(before.name());
            case CLASS -> new InlineClassCommitMessage(before.name());
            case PARAMETER -> new InlineParameterCommitMessage(before.name());
            default -> new DefaultInlineCommitMessage(before.type());
        };
    }

}
