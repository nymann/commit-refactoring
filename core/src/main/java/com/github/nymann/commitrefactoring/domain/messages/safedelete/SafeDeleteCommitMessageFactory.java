package com.github.nymann.commitrefactoring.domain.messages.safedelete;

import com.github.nymann.commitrefactoring.domain.CodeElement;
import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class SafeDeleteCommitMessageFactory {

    public static CommitMessage create(CodeElement deletedElement) {
        return switch (deletedElement.type()) {
            case CLASS -> new SafeDeleteClassCommitMessage(deletedElement.name());
            case FIELD -> new SafeDeleteFieldCommitMessage(deletedElement.name());
            case METHOD -> new SafeDeleteMethodCommitMessage(deletedElement.name());
            case PARAMETER -> new SafeDeleteParameterCommitMessage(deletedElement.name());
            default -> new DefaultSafeDeleteCommitMessage(deletedElement.type());
        };
    }
}
