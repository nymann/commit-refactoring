package com.github.nymann.commitrefactoring.messages.convertinstancemethod;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import org.jetbrains.annotations.NotNull;

public class ConvertToInstanceMethodFactory {
    public static @NotNull CommitMessage create(CodeElement before, CodeElement after) {
        if(before.type().equals(CodeElementType.METHOD)) {
            return new ConvertInstanceMethod(before.name());
        }

        return new DefaultConvertInstanceMethod();
    }
}
