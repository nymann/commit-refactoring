package com.github.nymann.commitrefactoring.domain.messages.makestatic;

import com.github.nymann.commitrefactoring.domain.CodeElement;
import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class MakeStaticFactory {

    public static CommitMessage create(CodeElement codeElement) {
        if(CodeElementType.METHOD.equals(codeElement.type())) {
            return new MakeMethodStaticCommitMessage(codeElement.name());
        }
        if(CodeElementType.CLASS.equals(codeElement.type())){
            return new MakeClassStaticCommitMessage(codeElement.name());
        }
        return new DefaultMakeStaticCommitMessage();
    }
}
