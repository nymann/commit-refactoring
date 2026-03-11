package com.github.nymann.commitrefactoring.domain.messages.extract;

import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.github.nymann.commitrefactoring.domain.CommitMessage;
import java.util.logging.Logger;

public class DefaultExtractCommitMessage implements CommitMessage {
    private static final Logger logger = Logger.getLogger(DefaultExtractCommitMessage.class.getName());

    public DefaultExtractCommitMessage(CodeElementType codeElementType) {
        logger.info("Unknown element type: " + codeElementType);
    }

    @Override
    public String getMessage() {
        return "Extract";
    }
}
