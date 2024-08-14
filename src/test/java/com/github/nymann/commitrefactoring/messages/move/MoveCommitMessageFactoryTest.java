package com.github.nymann.commitrefactoring.messages.move;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import junit.framework.TestCase;

public class MoveCommitMessageFactoryTest extends TestCase {

    public void testCreate() {
        CodeElement from = new CodeElement("Test", CodeElementType.CLASS);
        CodeElement to = new CodeElement("package", CodeElementType.PACKAGE);

        String actual = MoveCommitMessageFactory.create(from, to).getMessage();

        assertEquals("Move class 'Test' to 'package'", actual);
    }
}