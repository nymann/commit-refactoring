package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.RefactoringService;
import com.github.nymann.commitrefactoring.RefactoringTestBuilder;
import com.github.nymann.commitrefactoring.RefactoringType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExtractDelegateCommitMessageTest {

    private RefactoringService refactoringService;
    private RefactoringTestBuilder refactoringTestBuilder;

    @BeforeEach
    void setUp() {
        refactoringService = new RefactoringService();
        refactoringTestBuilder = new RefactoringTestBuilder().refactoringType(RefactoringType.EXTRACT_DELEGATE);
    }

    @Test
    void testExtractDelegateUnknown() {
        Refactoring refactoring = refactoringTestBuilder.build();

        refactoringService.addRefactoring(refactoring);

        String commitMessage = refactoringService.getCommitMessage();
        assertEquals("Extract delegate", commitMessage);
    }
}
