package com.github.nymann.commitrefactoring.domain.messages.safedelete;

import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.github.nymann.commitrefactoring.domain.CommitMessage;
import java.util.logging.Logger;

public class DefaultSafeDeleteCommitMessage implements CommitMessage {
    private static final Logger logger = Logger.getLogger(DefaultSafeDeleteCommitMessage.class.getName());

    public DefaultSafeDeleteCommitMessage(CodeElementType codeElementType) {
        logger.info("Unknown element type: " + codeElementType.name());
    }

    @Override
    public String getMessage() {
        return "Remove unused";
    }
}
