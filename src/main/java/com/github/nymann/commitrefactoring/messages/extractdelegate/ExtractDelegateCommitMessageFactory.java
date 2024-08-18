package com.github.nymann.commitrefactoring.messages.extractdelegate;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import org.jetbrains.annotations.NotNull;

public class ExtractDelegateCommitMessageFactory {
    public static @NotNull CommitMessage create(CodeElement before, CodeElement after) {
        if(before.type().equals(CodeElementType.CLASS)) {
            return new ExtractDelegateClassCommitMessage(before.name(), after.name());
        }
        return new DefaultExtractDelegateCommitMessage();
    }
}
