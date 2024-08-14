package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import junit.framework.TestCase;

public class RenameParameterCommitMessageTest extends TestCase {

    public void testGetMessage() {
        CodeElement before = new CodeElement("before", CodeElementType.PARAMETER);
        CodeElement after = new CodeElement("after", CodeElementType.PARAMETER);

        String actual = RenameCommitMessageFactory.create(before, after).getMessage();

        assertEquals("Rename parameter 'before' to 'after'", actual);
    }
}