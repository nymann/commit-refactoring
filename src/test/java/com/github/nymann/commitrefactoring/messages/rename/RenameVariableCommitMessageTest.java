package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import junit.framework.TestCase;

public class RenameVariableCommitMessageTest extends TestCase {

    public void testGetMessage() {
        CodeElement before = new CodeElement("before", CodeElementType.LOCAL_VARIABLE);
        CodeElement after = new CodeElement("after", CodeElementType.LOCAL_VARIABLE);

        String actual = RenameCommitMessageFactory.create(before, after).getMessage();

        assertEquals("Rename variable 'before' to 'after'", actual);
    }
}