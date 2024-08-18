package com.github.nymann.commitrefactoring.messages;


import com.github.nymann.commitrefactoring.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeSignatureCommitMessageTest {
    private RefactoringTestBuilder refactoringTestBuilder;
    private RefactoringService refactoringService;

    @BeforeEach
    void setUp() {
        refactoringTestBuilder = new RefactoringTestBuilder().refactoringType(RefactoringType.CHANGE_SIGNATURE);
        refactoringService = new RefactoringService();
    }

    @Test
    public void testUnknownChangeSignature() {
        Refactoring refactoring = refactoringTestBuilder.build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Change signature", refactoringService.getCommitMessage());
    }

    @Test
    public void testChangeMethodSignature() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeName("foo")
                .beforeType(CodeElementType.METHOD)
                .afterName("foo")
                .afterType(CodeElementType.METHOD)
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Change method signature of 'foo'", refactoringService.getCommitMessage());
    }

    @Test
    public void testChangeClassSignature() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeName("Foo")
                .beforeType(CodeElementType.CLASS)
                .afterName("Foo")
                .afterType(CodeElementType.CLASS)
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Change class signature of 'Foo'", refactoringService.getCommitMessage());

    }

}