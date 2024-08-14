package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import junit.framework.TestCase;

public class DefaultSafeDeleteCommitMessageTest extends TestCase {

    public void testGetMessage() {
        CodeElement codeElement = new CodeElement("test", CodeElementType.UNKNOWN);
        String actual = SafeDeleteCommitMessageFactory.create(codeElement).getMessage();
        assertEquals("Remove unused", actual);
    }
}