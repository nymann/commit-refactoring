package com.github.nymann.commitrefactoring;

import org.jetbrains.annotations.NotNull;

public class MakeStaticFactory {

    public static @NotNull CommitMessage create(CodeElement codeElement) {
        return new DefaultMakeStaticCommitMessage();
    }
}
