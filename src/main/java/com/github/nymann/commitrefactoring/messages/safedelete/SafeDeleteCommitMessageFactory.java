package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CommitMessage;

public class SafeDeleteCommitMessageFactory {

    public static CommitMessage create(CodeElement deletedElement) {
        return switch (deletedElement.getType()) {
            case CLASS -> new SafeDeleteClassCommitMessage(deletedElement.getName());
            case FIELD -> new SafeDeleteFieldCommitMessage(deletedElement.getName());
            case METHOD -> new SafeDeleteMethodCommitMessage(deletedElement.getName());
            case PARAMETER -> new SafeDeleteParameterCommitMessage(deletedElement.getName());
            default -> new DefaultSafeDeleteCommitMessage(deletedElement.getType());
        };
    }
}
