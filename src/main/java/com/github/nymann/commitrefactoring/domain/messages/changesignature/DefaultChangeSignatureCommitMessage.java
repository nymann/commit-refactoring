package com.github.nymann.commitrefactoring.domain.messages.changesignature;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class DefaultChangeSignatureCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Change signature";
    }
}
