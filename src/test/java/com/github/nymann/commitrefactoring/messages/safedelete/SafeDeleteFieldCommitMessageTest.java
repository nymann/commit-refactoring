package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import junit.framework.TestCase;

public class SafeDeleteFieldCommitMessageTest extends TestCase {

    public void testGetMessage() {
        CodeElement codeElement = new CodeElement("test", CodeElementType.FIELD);

        String actual = SafeDeleteCommitMessageFactory.create(codeElement).getMessage();

        assertEquals("Remove unused field 'test'", actual);
    }

}