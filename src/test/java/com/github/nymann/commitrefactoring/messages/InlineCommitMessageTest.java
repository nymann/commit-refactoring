package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InlineCommitMessageTest {
    private RefactoringService refactoringService;
    private RefactoringTestBuilder refactoringTestBuilder;

    @BeforeEach
    void setUp() {
        refactoringService = new RefactoringService();
        refactoringTestBuilder = new RefactoringTestBuilder().refactoringType(RefactoringType.INLINE);
    }

    @Test
    void testInlineUnknown() {
        Refactoring refactoring = refactoringTestBuilder.build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Inline", refactoringService.getCommitMessage());
    }

    @Test
    void testInlineClass() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.CLASS)
                .beforeName("Test")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Inline class 'Test'", refactoringService.getCommitMessage());
    }

    @Test
    public void testInlineConstructor() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.CONSTRUCTOR)
                .beforeName("Test")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Inline constructor in 'Test'", refactoringService.getCommitMessage());
    }

    @Test
    public void testInlineMethod() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.METHOD)
                .beforeName("test")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Inline method 'test'", refactoringService.getCommitMessage());
    }

    @Test
    public void testInlineParameter() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.PARAMETER)
                .beforeName("test")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Inline parameter 'test'", refactoringService.getCommitMessage());
    }

    @Test
    public void testInlineField() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.FIELD)
                .beforeName("test")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Inline field 'test'", refactoringService.getCommitMessage());
    }
    @Test
    public void testInlineVariable() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.LOCAL_VARIABLE)
                .beforeName("test")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Inline variable 'test'", refactoringService.getCommitMessage());
    }
}
