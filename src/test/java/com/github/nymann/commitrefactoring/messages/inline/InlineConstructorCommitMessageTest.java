package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import junit.framework.TestCase;

public class InlineConstructorCommitMessageTest extends TestCase {

    public void testGetMessage() {
        CodeElement element = new CodeElement("InlineExampleClass", CodeElementType.CONSTRUCTOR);

        String actual = InlineCommitMessageFactory.create(element).getMessage();

        assertEquals("Inline constructor in 'InlineExampleClass'", actual);
    }
}