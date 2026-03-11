package com.github.nymann.commitrefactoring.domain.messages;

import com.github.nymann.commitrefactoring.domain.*;
import com.github.nymann.commitrefactoring.application.RefactoringService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ExtractCommitMessageTest {

    private RefactoringService refactoringService;
    private RefactoringTestBuilder refactoringTestBuilder;

    @BeforeEach
    void setUp() {
        refactoringService = new RefactoringService();
        refactoringTestBuilder = new RefactoringTestBuilder().refactoringType(RefactoringType.EXTRACT);
    }

    @Test
    public void testExtractMethod() {
        Refactoring refactoring = refactoringTestBuilder
                .afterType(CodeElementType.METHOD)
                .afterName("test")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Extract method 'test'", refactoringService.getCommitMessage());
    }


    @Test
    public void testUnknownExtractMethod() {
        Refactoring refactoring = refactoringTestBuilder
                .afterType(CodeElementType.UNKNOWN)
                .afterName("test")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Extract", refactoringService.getCommitMessage());
    }

    @Test
    public void testExtractVariable() {
        Refactoring refactoring = refactoringTestBuilder
                .afterType(CodeElementType.LOCAL_VARIABLE)
                .afterName("test")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Extract variable 'test'", refactoringService.getCommitMessage());
    }
}