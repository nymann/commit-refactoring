package com.github.nymann.commitrefactoring.messages.move;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import junit.framework.TestCase;

public class MovePackageIntoPackageTest extends TestCase {

    public void testCreate() {
        CodeElement from = new CodeElement("a", CodeElementType.PACKAGE);
        CodeElement to = new CodeElement("b", CodeElementType.PACKAGE);

        String actual = MoveCommitMessageFactory.create(from, to).getMessage();

        assertEquals("Move package 'a' into 'b'", actual);
    }
}