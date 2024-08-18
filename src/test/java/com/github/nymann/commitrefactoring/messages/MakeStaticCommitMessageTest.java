package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.RefactoringService;
import com.github.nymann.commitrefactoring.RefactoringTestBuilder;
import com.github.nymann.commitrefactoring.RefactoringType;
import org.junit.jupiter.api.BeforeEach;
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
    public void testUnknownChangeSignature() {
        Refactoring refactoring = refactoringTestBuilder.build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("Make static", refactoringService.getCommitMessage());
    }
}
