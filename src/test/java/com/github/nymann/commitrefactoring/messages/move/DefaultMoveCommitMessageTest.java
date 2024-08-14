package com.github.nymann.commitrefactoring.messages.move;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import junit.framework.TestCase;

public class DefaultMoveCommitMessageTest extends TestCase {

    public void testGetMessage() {
        CodeElement codeElement = new CodeElement("test", CodeElementType.UNKNOWN);

        String actual = MoveCommitMessageFactory.create(codeElement, codeElement).getMessage();

        assertEquals("Move", actual);
    }
}