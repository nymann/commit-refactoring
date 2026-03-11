package com.github.nymann.commitrefactoring.domain.messages.inline;

import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.github.nymann.commitrefactoring.domain.CommitMessage;
import java.util.logging.Logger;

public class DefaultInlineCommitMessage implements CommitMessage {
    private static final Logger logger = Logger.getLogger(DefaultInlineCommitMessage.class.getName());

    public DefaultInlineCommitMessage(CodeElementType codeElementType) {
        logger.warning("Unknown element type: " + codeElementType.name());
    }

    @Override
    public String getMessage() {
        return "Inline";
    }
}
