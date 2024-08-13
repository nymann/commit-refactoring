package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;

public class ChangeSignatureCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Change Signature";
    }
}
