package com.github.nymann.commitrefactoring.domain.messages.rename;

import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.github.nymann.commitrefactoring.domain.CommitMessage;
import java.util.logging.Logger;

public class DefaultRenameCommitMessage implements CommitMessage {
    private static final Logger logger = Logger.getLogger(DefaultRenameCommitMessage.class.getName());

    public DefaultRenameCommitMessage(CodeElementType codeElementType) {
        logger.info("Unknown element type: " + codeElementType.name());
    }

    @Override
    public String getMessage() {
        return "Rename";
    }
}
