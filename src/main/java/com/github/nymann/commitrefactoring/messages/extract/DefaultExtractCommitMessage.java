package com.github.nymann.commitrefactoring.messages.extract;

import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.openapi.diagnostic.Logger;

public class DefaultExtractCommitMessage implements CommitMessage {
    private static final Logger logger = Logger.getInstance(DefaultExtractCommitMessage.class);

    public DefaultExtractCommitMessage(CodeElementType codeElementType) {
        logger.info("Unknown element type: " + codeElementType);
    }

    @Override
    public String getMessage() {
        return "Extract";
    }
}
