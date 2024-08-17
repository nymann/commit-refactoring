package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenameParameterCommitMessageTest {

    @Test
    public void testGetMessage() {
        CodeElement before = new CodeElement("before", CodeElementType.PARAMETER);
        CodeElement after = new CodeElement("after", CodeElementType.PARAMETER);

        String actual = RenameCommitMessageFactory.create(before, after).getMessage();

        assertEquals("Rename parameter 'before' to 'after'", actual);
    }
}