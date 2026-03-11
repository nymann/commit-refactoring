package com.github.nymann.commitrefactoring.domain.messages.extractdelegate;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class DefaultExtractDelegateCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Extract delegate";
    }
}
