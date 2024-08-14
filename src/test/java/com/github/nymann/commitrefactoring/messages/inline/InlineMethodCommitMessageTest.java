package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import junit.framework.TestCase;

public class InlineMethodCommitMessageTest extends TestCase {

    public void testGetMessage() {
        CodeElement test = new CodeElement("test", CodeElementType.METHOD);
        CommitMessage commitMessage = new InlineMethodCommitMessage(test);

        String actual = commitMessage.getMessage();

        assertEquals("Inline method 'test'", actual);
    }
}