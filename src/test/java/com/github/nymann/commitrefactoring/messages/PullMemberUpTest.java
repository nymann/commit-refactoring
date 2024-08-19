package com.github.nymann.commitrefactoring.messages;


import com.github.nymann.commitrefactoring.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PullMemberUpTest {
    private RefactoringTestBuilder refactoringTestBuilder;
    private RefactoringService refactoringService;

    @BeforeEach
    void setUp() {
        refactoringTestBuilder = new RefactoringTestBuilder().refactoringType(RefactoringType.PULL_MEMBERS_UP);
        refactoringService = new RefactoringService();
    }

    @Test
    public void testUnknownChangeSignature() {
        Refactoring refactoring = refactoringTestBuilder.build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Pull members up", refactoringService.getCommitMessage());
    }

    @Test
    public void testClassPullMembersUp() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeName("ChildA")
                .beforeType(CodeElementType.CLASS)
                .afterName("Parent")
                .afterType(CodeElementType.CLASS)
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Pull members up to 'Parent'", refactoringService.getCommitMessage());
    }
}