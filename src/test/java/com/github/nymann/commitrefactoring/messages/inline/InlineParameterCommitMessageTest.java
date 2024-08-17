package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InlineParameterCommitMessageTest {

    @Test
    public void testCommitMessage() {
        CodeElement element = new CodeElement("test", CodeElementType.PARAMETER);

        String actual = InlineCommitMessageFactory.create(element).getMessage();

        assertEquals("Inline parameter 'test'", actual);
    }
}