package com.github.nymann.commitrefactoring.messages;


import com.github.nymann.commitrefactoring.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenameCommitMessageTest {
    private RefactoringTestBuilder refactoringTestBuilder;
    private RefactoringService refactoringService;

    @BeforeEach
    void setUp() {
        refactoringTestBuilder = new RefactoringTestBuilder().refactoringType(RefactoringType.RENAME);
        refactoringService = new RefactoringService();
    }

    @Test
    public void testRenameVariable() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.LOCAL_VARIABLE)
                .afterType(CodeElementType.LOCAL_VARIABLE)
                .beforeName("a")
                .afterName("b")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Rename variable 'a' to 'b'", refactoringService.getCommitMessage());
    }

    @Test
    public void testRenameParameter() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.PARAMETER)
                .afterType(CodeElementType.PARAMETER)
                .beforeName("a")
                .afterName("b")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Rename parameter 'a' to 'b'", refactoringService.getCommitMessage());
    }

    @Test
    public void testRenameMethod() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.METHOD)
                .afterType(CodeElementType.METHOD)
                .beforeName("a")
                .afterName("b")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Rename method 'a' to 'b'", refactoringService.getCommitMessage());
    }

    @Test
    public void testRenameClass() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.CLASS)
                .afterType(CodeElementType.CLASS)
                .beforeName("A")
                .afterName("B")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Rename class 'A' to 'B'", refactoringService.getCommitMessage());
    }

    @Test
    public void testRenameField() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.FIELD)
                .afterType(CodeElementType.FIELD)
                .beforeName("a")
                .afterName("b")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Rename field 'a' to 'b'", refactoringService.getCommitMessage());
    }
    @Test
    public void testRenameUnknown() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.UNKNOWN)
                .afterType(CodeElementType.UNKNOWN)
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Rename", refactoringService.getCommitMessage());
    }
}