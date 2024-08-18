package com.github.nymann.commitrefactoring;

import com.github.nymann.commitrefactoring.messages.extractdelegate.DefaultExtractDelegateCommitMessage;
import com.github.nymann.commitrefactoring.messages.extractdelegate.ExtractDelegateClassCommitMessage;
import org.jetbrains.annotations.NotNull;

public class ExtractDelegateCommitMessageFactory {
    public static @NotNull CommitMessage create(CodeElement before, CodeElement after) {
        if(before.type().equals(CodeElementType.CLASS)) {
            return new ExtractDelegateClassCommitMessage(before.name(), after.name());
        }
        return new DefaultExtractDelegateCommitMessage();
    }
}
