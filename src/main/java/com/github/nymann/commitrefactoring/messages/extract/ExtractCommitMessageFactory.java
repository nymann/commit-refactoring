package com.github.nymann.commitrefactoring.messages.extract;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CommitMessage;

public class ExtractCommitMessageFactory {

    public static CommitMessage create(CodeElement after) {
        return switch (after.type()) {
            case LOCAL_VARIABLE -> new ExtractVariableCommitMessage(after.name());
            case METHOD -> new ExtractMethodCommitMessage(after.name());
            default -> new DefaultExtractCommitMessage(after.type());
        };
    }
}
