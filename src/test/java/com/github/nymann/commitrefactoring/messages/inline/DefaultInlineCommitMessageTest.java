package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultInlineCommitMessageTest {

    @Test
    public void test() {
        CodeElement element = new CodeElement("test", CodeElementType.UNKNOWN);
        CommitMessage commitMessage = InlineCommitMessageFactory.create(element);

        String actual = commitMessage.getMessage();
        assertEquals("Inline", actual);
    }
}