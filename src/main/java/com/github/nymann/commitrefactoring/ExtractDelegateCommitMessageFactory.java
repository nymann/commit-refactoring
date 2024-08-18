package com.github.nymann.commitrefactoring;

import com.github.nymann.commitrefactoring.messages.extractdelegate.DefaultExtractDelegateCommitMessage;
import org.jetbrains.annotations.NotNull;

public class ExtractDelegateCommitMessageFactory {
    public static @NotNull CommitMessage create(CodeElement before, CodeElement after) {
        return new DefaultExtractDelegateCommitMessage();
    }
}
