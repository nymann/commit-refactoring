package com.github.nymann.commitrefactoring.messages;

import junit.framework.TestCase;

public class DefaultChangeSignatureCommitMessageTest extends TestCase {

    public void testGetMessage() {
        String actual = new DefaultChangeSignatureCommitMessage().getMessage();

        assertEquals("Change signature", actual);
    }
}