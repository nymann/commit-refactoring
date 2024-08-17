package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenameMethodCommitMessageTest {

    @Test
    public void testGetMessage() {
        CodeElement before = new CodeElement("Before", CodeElementType.METHOD);
        CodeElement after = new CodeElement("After", CodeElementType.METHOD);
        CommitMessage commitMessage = RenameCommitMessageFactory.create(before, after);

        String actual = commitMessage.getMessage();

        assertEquals("Rename method 'Before' to 'After'", actual);
    }
}