package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SafeDeleteClassCommitMessageTest {

    @Test
    public void testGetMessage() {
        CodeElement codeElement = new CodeElement("test", CodeElementType.CLASS);

        String actual = SafeDeleteCommitMessageFactory.create(codeElement).getMessage();

        assertEquals("Remove unused class 'test'", actual);
    }

}