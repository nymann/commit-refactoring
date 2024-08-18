package com.github.nymann.commitrefactoring.messages.convertinstancemethod;

import com.github.nymann.commitrefactoring.CommitMessage;

public class DefaultConvertInstanceMethod implements CommitMessage {
    @Override
    public String getMessage() {
        return "Convert instance method";
    }
}
