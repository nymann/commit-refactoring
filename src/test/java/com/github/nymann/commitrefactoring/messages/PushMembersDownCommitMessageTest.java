package com.github.nymann.commitrefactoring.messages;


import com.github.nymann.commitrefactoring.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PushMembersDownCommitMessageTest {
    private RefactoringTestBuilder refactoringTestBuilder;
    private RefactoringService refactoringService;

    @BeforeEach
    void setUp() {
        refactoringTestBuilder = new RefactoringTestBuilder().refactoringType(RefactoringType.PUSH_MEMBERS_DOWN);
        refactoringService = new RefactoringService();
    }

    @Test
    public void testPushUnknownMemberDown() {
        Refactoring refactoring = refactoringTestBuilder
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Push members down", refactoringService.getCommitMessage());
    }

    @Test
    public void testPushMemberDown() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.CLASS)
                .beforeName("Display")
                .afterType(CodeElementType.CLASS)
                .afterName("Component")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Push members down from 'Display' to 'Component'", refactoringService.getCommitMessage());
    }
}