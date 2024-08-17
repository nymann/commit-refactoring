package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenameClassCommitMessageTest {

    @Test
    public void testGetMessage() {
        CodeElement before = new CodeElement("Before", CodeElementType.CLASS);
        CodeElement after = new CodeElement("After", CodeElementType.CLASS);
        CommitMessage commitMessage = RenameCommitMessageFactory.create(before, after);

        String actual = commitMessage.getMessage();

        assertEquals("Rename class 'Before' to 'After'", actual);
    }
}