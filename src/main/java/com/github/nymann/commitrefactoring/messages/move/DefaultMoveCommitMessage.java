package com.github.nymann.commitrefactoring.messages.move;

import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.openapi.diagnostic.Logger;

public class DefaultMoveCommitMessage implements CommitMessage {
    private static final Logger logger = Logger.getInstance(DefaultMoveCommitMessage.class);

    public DefaultMoveCommitMessage(CodeElementType from, CodeElementType to) {
        logger.info("Unknown element types, from: '" + from.name() + "', to: '" + to.name() + "'");
    }

    @Override
    public String getMessage() {
        return "Move";
    }
}
