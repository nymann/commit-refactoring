package com.github.nymann.commitrefactoring.messages;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultChangeSignatureCommitMessageTest {

    @Test
    public void testGetMessage() {
        String actual = new DefaultChangeSignatureCommitMessage().getMessage();

        assertEquals("Change signature", actual);
    }
}