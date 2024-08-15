package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CommitMessage;

public class RenameCommitMessageFactory {

    public static CommitMessage create(CodeElement before, CodeElement after) {
        return switch (before.type()) {
            case CLASS -> new RenameClassCommitMessage(before.name(), after.name());
            case FIELD -> new RenameFieldCommitMessage(before.name(), after.name());
            case METHOD -> new RenameMethodCommitMessage(before.name(), after.name());
            case PARAMETER -> new RenameParameterCommitMessage(before.name(), after.name());
            case LOCAL_VARIABLE -> new RenameVariableCommitMessage(before.name(), after.name());
            default -> new DefaultRenameCommitMessage(before.type());
        };
    }
}
