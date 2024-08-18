package com.github.nymann.commitrefactoring.messages.extractdelegate;

import com.github.nymann.commitrefactoring.CommitMessage;

public class DefaultExtractDelegateCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Extract delegate";
    }
}
