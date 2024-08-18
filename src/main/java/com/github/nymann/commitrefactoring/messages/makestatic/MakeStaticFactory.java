package com.github.nymann.commitrefactoring.messages.makestatic;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import org.jetbrains.annotations.NotNull;

public class MakeStaticFactory {

    public static @NotNull CommitMessage create(CodeElement codeElement) {
        if(CodeElementType.METHOD.equals(codeElement.type())) {
            return new MakeMethodStaticCommitMessage(codeElement.name());
        }
        return new DefaultMakeStaticCommitMessage();
    }
}
