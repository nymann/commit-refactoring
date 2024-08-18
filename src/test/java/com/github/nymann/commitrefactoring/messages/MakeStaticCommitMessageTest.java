package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MakeStaticCommitMessageTest {

    private RefactoringTestBuilder refactoringTestBuilder;
    private RefactoringService refactoringService;

    @BeforeEach
    void setUp() {
        refactoringTestBuilder = new RefactoringTestBuilder().refactoringType(RefactoringType.MAKE_STATIC);
        refactoringService = new RefactoringService();
    }

    @Test
    public void testUnknownMakeStatic() {
        Refactoring refactoring = refactoringTestBuilder.build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Make static", refactoringService.getCommitMessage());
    }

    @Test
    public void testMakeMethodStatic() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.METHOD)
                .beforeName("foo")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Make method 'foo' static", refactoringService.getCommitMessage());
    }

    @Test
    public void testMakeClassStatic() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeType(CodeElementType.CLASS)
                .beforeName("Foo")
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Make class 'Foo' static", refactoringService.getCommitMessage());
    }
}
