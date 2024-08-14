package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CommitMessage;

public class RenameCommitMessageFactory {

    public static CommitMessage create(CodeElement before, CodeElement after) {
        return switch (before.getType()) {
            case CLASS -> new RenameClassCommitMessage(before.getName(), after.getName());
            case FIELD -> new RenameFieldCommitMessage(before.getName(), after.getName());
            case METHOD -> new RenameMethodCommitMessage(before.getName(), after.getName());
            case PARAMETER -> new RenameParameterCommitMessage(before.getName(), after.getName());
            case LOCAL_VARIABLE -> new RenameVariableCommitMessage(before.getName(), after.getName());
            default -> new DefaultRenameCommitMessage(before.getType());
        };
    }
}
