package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenameFieldCommitMessageTest {

    @Test
    public void testGetMessage() {
        CodeElement before = new CodeElement("before", CodeElementType.FIELD);
        CodeElement after = new CodeElement("after", CodeElementType.FIELD);
        CommitMessage commitMessage = RenameCommitMessageFactory.create(before, after);

        String actual = commitMessage.getMessage();

        assertEquals("Rename field 'before' to 'after'", actual);
    }
}