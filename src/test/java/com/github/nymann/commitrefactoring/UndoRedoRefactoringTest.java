package com.github.nymann.commitrefactoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UndoRedoRefactoringTest {
    private RefactoringService refactoringService;

    @BeforeEach
    void setUp() {
        refactoringService = new RefactoringService();
    }

    @Test
    void testUndoingNothingProducesSameMessage() {
        String before = refactoringService.getCommitMessage();
        refactoringService.undoLastRefactoring();
        String after = refactoringService.getCommitMessage();
        assertEquals(before, after);
    }

    @Test
    void testUndoRedoAndClear() {
        String firstMessage = refactoringService.getCommitMessage();
        refactoringService.addRefactoring(RefactoringTestBuilder
                .builder()
                .refactoringType(RefactoringType.EXTRACT)
                .build());
        String secondMessage = refactoringService.getCommitMessage();
        assertNotEquals(firstMessage, secondMessage);
        refactoringService.undoLastRefactoring();
        assertEquals(firstMessage, refactoringService.getCommitMessage());
        refactoringService.redoLastRefactoring();
        assertEquals(secondMessage, refactoringService.getCommitMessage());
        refactoringService.clearRefactorings();
        assertEquals(firstMessage, refactoringService.getCommitMessage());
    }
}
