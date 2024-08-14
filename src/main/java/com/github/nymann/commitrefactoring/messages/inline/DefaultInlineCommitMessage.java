package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.openapi.diagnostic.Logger;

public class DefaultInlineCommitMessage implements CommitMessage {
    private static final Logger logger = Logger.getInstance(DefaultInlineCommitMessage.class);
    public DefaultInlineCommitMessage(CodeElementType codeElementType) {
        logger.info("Unknown element type: " + codeElementType);
    }
    @Override
    public String getMessage() {
        return "Inline";
    }
}
