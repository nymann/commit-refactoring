package com.github.nymann.commitrefactoring.messages.extract;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import junit.framework.TestCase;

public class DefaultExtractCommitMessageTest extends TestCase {

    public void testGetMessage() {
        CodeElement codeElement = new CodeElement("test", CodeElementType.UNKNOWN);
        CommitMessage commitMessage = ExtractCommitMessageFactory.create(codeElement);

        String actual = commitMessage.getMessage();

        assertEquals(actual, "Extract");
    }
}