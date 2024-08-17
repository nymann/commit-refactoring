package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InlineMethodCommitMessageTest {

    @Test
    public void testGetMessage() {
        CodeElement element = new CodeElement("test", CodeElementType.METHOD);
        CommitMessage commitMessage = InlineCommitMessageFactory.create(element);

        String actual = commitMessage.getMessage();

        assertEquals("Inline method 'test'", actual);
    }
}