package com.github.nymann.commitrefactoring.messages.extract;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import junit.framework.TestCase;

public class ExtractMethodCommitMessageTest extends TestCase {

    public void testGetMessage() {
        CodeElement codeElement = new CodeElement("test", CodeElementType.METHOD);
        CommitMessage commitMessage = ExtractCommitMessageFactory.create(codeElement);

        String actual = commitMessage.getMessage();

        assertEquals("Extract method 'test'", actual);
    }
}