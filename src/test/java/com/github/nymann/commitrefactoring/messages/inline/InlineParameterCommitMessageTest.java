package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import junit.framework.TestCase;

public class InlineParameterCommitMessageTest extends TestCase {
    public void testCommitMessage() {
        CodeElement element = new CodeElement("test", CodeElementType.PARAMETER);

        String actual = InlineCommitMessageFactory.create(element).getMessage();

        assertEquals("Inline parameter 'test'", actual);
    }
}