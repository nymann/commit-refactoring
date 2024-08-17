package com.github.nymann.commitrefactoring.messages.move;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovePackageIntoPackageTest {

    @Test
    public void testCreate() {
        CodeElement from = new CodeElement("a", CodeElementType.PACKAGE);
        CodeElement to = new CodeElement("b", CodeElementType.PACKAGE);

        String actual = MoveCommitMessageFactory.create(from, to).getMessage();

        assertEquals("Move package 'a' into 'b'", actual);
    }
}