package com.github.nymann.commitrefactoring.messages.extract;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CommitMessage;

public class ExtractCommitMessageFactory {

    public static CommitMessage create(CodeElement after) {
        return switch (after.getType()) {
            case LOCAL_VARIABLE -> new ExtractVariableCommitMessage(after.getName());
            case METHOD -> new ExtractMethodCommitMessage(after.getName());
            default -> new DefaultExtractCommitMessage(after.getType());
        };
    }
}
