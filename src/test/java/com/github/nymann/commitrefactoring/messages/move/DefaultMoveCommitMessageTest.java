package com.github.nymann.commitrefactoring.messages.move;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultMoveCommitMessageTest {

    @Test
    public void testGetMessage() {
        CodeElement codeElement = new CodeElement("test", CodeElementType.UNKNOWN);

        String actual = MoveCommitMessageFactory.create(codeElement, codeElement).getMessage();

        assertEquals("Move", actual);
    }
}