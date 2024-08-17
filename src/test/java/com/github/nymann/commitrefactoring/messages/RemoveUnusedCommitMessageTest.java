package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveUnusedCommitMessageTest {
    private RefactoringTestBuilder refactoringTestBuilder;
    private RefactoringService refactoringService;

    @BeforeEach
    void setUp() {
        refactoringTestBuilder = new RefactoringTestBuilder().refactoringType(RefactoringType.SAFE_DELETE);
        refactoringService = new RefactoringService();
    }


    @Test
    public void testRemoveUnusedUnknown() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.UNKNOWN)
                .build();

        refactoringService.addRefactoring(refactoring);
        assertEquals("Remove unused", refactoringService.getCommitMessage());
    }

    @Test
    public void testRemoveUnusedClass() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeName("Test")
                .beforeType(CodeElementType.CLASS)
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Remove unused class 'Test'", refactoringService.getCommitMessage());
    }

    @Test
    public void testRemoveUnusedField() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeName("test")
                .beforeType(CodeElementType.FIELD)
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Remove unused field 'test'", refactoringService.getCommitMessage());
    }


    @Test
    public void testRemoveUnusedParameter() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeName("test")
                .beforeType(CodeElementType.PARAMETER)
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Remove unused parameter 'test'", refactoringService.getCommitMessage());
    }

    @Test
    public void testRemoveUnusedMethod() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeName("test")
                .beforeType(CodeElementType.METHOD)
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Remove unused method 'test'", refactoringService.getCommitMessage());
    }
}
