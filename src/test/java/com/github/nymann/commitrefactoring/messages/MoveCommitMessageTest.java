package com.github.nymann.commitrefactoring.messages;


import com.github.nymann.commitrefactoring.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveCommitMessageTest {
    private RefactoringTestBuilder refactoringTestBuilder;
    private RefactoringService refactoringService;

    @BeforeEach
    void setUp() {
        refactoringTestBuilder = new RefactoringTestBuilder()
                .refactoringType(RefactoringType.MOVE)
                .afterName("test")
                .afterType(CodeElementType.PACKAGE);
        refactoringService = new RefactoringService();
    }

    @Test
    public void testUnknownMove() {
        Refactoring refactoring = refactoringTestBuilder
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Move", refactoringService.getCommitMessage());
    }

    @Test
    public void testMoveClassToPackage() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeName("A")
                .beforeType(CodeElementType.CLASS)
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Move class 'A' to 'test'", refactoringService.getCommitMessage());
    }

    @Test
    public void testMovePackageIntoPackage() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeName("cat")
                .beforeType(CodeElementType.PACKAGE)
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Move package 'cat' into 'test'", refactoringService.getCommitMessage());
    }
}