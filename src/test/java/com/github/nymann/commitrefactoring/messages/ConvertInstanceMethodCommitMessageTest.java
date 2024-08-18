package com.github.nymann.commitrefactoring.messages;


import com.github.nymann.commitrefactoring.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertInstanceMethodCommitMessageTest {
    private RefactoringTestBuilder refactoringTestBuilder;
    private RefactoringService refactoringService;

    @BeforeEach
    void setUp() {
        refactoringTestBuilder = new RefactoringTestBuilder().refactoringType(RefactoringType.CONVERT_INSTANCE_METHOD);
        refactoringService = new RefactoringService();
    }

    @Test
    public void testUnknown() {
        Refactoring refactoring = refactoringTestBuilder.build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Convert instance method", refactoringService.getCommitMessage());
    }

    @Test
    public void testConvertInstanceMethod() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeName("foo")
                .beforeType(CodeElementType.METHOD)
                .afterName("Foo")
                .afterType(CodeElementType.CLASS)
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Convert 'foo' to instance method", refactoringService.getCommitMessage());
    }

}