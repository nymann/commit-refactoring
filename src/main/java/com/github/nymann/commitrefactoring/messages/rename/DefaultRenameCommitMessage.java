package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.openapi.diagnostic.Logger;

public class DefaultRenameCommitMessage implements CommitMessage {
    private static final Logger logger = Logger.getInstance(DefaultRenameCommitMessage.class);

    public DefaultRenameCommitMessage(CodeElementType codeElementType) {
        logger.info("Unknown element type: " + codeElementType);
    }

    @Override
    public String getMessage() {
        return "Rename";
    }
}
