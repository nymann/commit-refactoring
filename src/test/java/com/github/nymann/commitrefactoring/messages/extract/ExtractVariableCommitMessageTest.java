package com.github.nymann.commitrefactoring.messages.extract;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import junit.framework.TestCase;

public class ExtractVariableCommitMessageTest extends TestCase {

    public void testGetMessage() {
        CodeElement codeElement = new CodeElement("test", CodeElementType.LOCAL_VARIABLE);
        CommitMessage commitMessage = ExtractCommitMessageFactory.create(codeElement);

        String actual = commitMessage.getMessage();

        assertEquals(actual, "Extract variable 'test'");
    }
}