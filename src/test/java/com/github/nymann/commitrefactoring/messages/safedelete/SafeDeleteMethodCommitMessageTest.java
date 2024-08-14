package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import junit.framework.TestCase;

public class SafeDeleteMethodCommitMessageTest extends TestCase {

    public void testGetMessage() {
        CodeElement codeElement = new CodeElement("test", CodeElementType.METHOD);

        String actual = SafeDeleteCommitMessageFactory.create(codeElement).getMessage();

        assertEquals("Remove unused method 'test'", actual);
    }

}