package com.github.nymann.commitrefactoring.domain.messages.move;

import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.github.nymann.commitrefactoring.domain.CommitMessage;
import java.util.logging.Logger;

public class DefaultMoveCommitMessage implements CommitMessage {
    private static final Logger logger = Logger.getLogger(DefaultMoveCommitMessage.class.getName());

    public DefaultMoveCommitMessage(CodeElementType from, CodeElementType to) {
        logger.info("Unknown element types, from: '" + from.name() + "', to: '" + to.name() + "'");
    }

    @Override
    public String getMessage() {
        return "Move";
    }
}
