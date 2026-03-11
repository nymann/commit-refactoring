package com.github.nymann.commitrefactoring.domain.messages.convertinstancemethod;

import com.github.nymann.commitrefactoring.domain.CodeElement;
import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class ConvertToInstanceMethodFactory {
    public static CommitMessage create(CodeElement before, CodeElement after) {
        if(before.type().equals(CodeElementType.METHOD)) {
            return new ConvertInstanceMethod(before.name());
        }

        return new DefaultConvertInstanceMethod();
    }
}
