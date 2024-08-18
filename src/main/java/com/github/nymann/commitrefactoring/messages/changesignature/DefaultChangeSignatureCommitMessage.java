package com.github.nymann.commitrefactoring.messages.changesignature;

import com.github.nymann.commitrefactoring.CommitMessage;

public class DefaultChangeSignatureCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Change signature";
    }
}
