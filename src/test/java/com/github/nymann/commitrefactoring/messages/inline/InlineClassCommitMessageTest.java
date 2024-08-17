package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InlineClassCommitMessageTest {

    @Test
    public void testGetMessage() {
        CodeElement element = new CodeElement("Test", CodeElementType.CLASS);

        String actual = InlineCommitMessageFactory.create(element).getMessage();

        assertEquals("Inline class 'Test'", actual);
    }
}