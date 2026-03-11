package com.github.nymann.commitrefactoring.domain.messages.extractdelegate;

import com.github.nymann.commitrefactoring.domain.CodeElement;
import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class ExtractDelegateCommitMessageFactory {
    public static CommitMessage create(CodeElement before, CodeElement after) {
        if(before.type().equals(CodeElementType.CLASS)) {
            return new ExtractDelegateClassCommitMessage(before.name(), after.name());
        }
        return new DefaultExtractDelegateCommitMessage();
    }
}
