package com.github.nymann.commitrefactoring.domain.messages.convertinstancemethod;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class DefaultConvertInstanceMethod implements CommitMessage {
    @Override
    public String getMessage() {
        return "Convert instance method";
    }
}
