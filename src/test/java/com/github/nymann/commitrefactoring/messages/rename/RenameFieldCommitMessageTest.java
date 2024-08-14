package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import junit.framework.TestCase;

public class RenameFieldCommitMessageTest extends TestCase {

    public void testGetMessage() {
        CodeElement before = new CodeElement("before", CodeElementType.FIELD);
        CodeElement after = new CodeElement("after", CodeElementType.FIELD);
        CommitMessage commitMessage = RenameCommitMessageFactory.create(before, after);

        String actual = commitMessage.getMessage();

        assertEquals("Rename field 'before' to 'after'", actual);
    }
}