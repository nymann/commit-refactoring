package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.openapi.diagnostic.Logger;

public class DefaultSafeDeleteCommitMessage implements CommitMessage {
    private static final Logger logger = Logger.getInstance(DefaultSafeDeleteCommitMessage.class);

    public DefaultSafeDeleteCommitMessage(CodeElementType codeElementType) {
        logger.info("Unknown element type: " + codeElementType.name());
    }

    @Override
    public String getMessage() {
        return "Remove unused";
    }
}
