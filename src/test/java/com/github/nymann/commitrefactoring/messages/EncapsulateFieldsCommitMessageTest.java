package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncapsulateFieldsCommitMessageTest {

    private RefactoringService refactoringService;
    private RefactoringTestBuilder refactoringTestBuilder;

    @BeforeEach
    void setUp() {
        refactoringService = new RefactoringService();
        refactoringTestBuilder = new RefactoringTestBuilder().refactoringType(RefactoringType.ENCAPSULATE_FIELDS);
    }

    @Test
    void testEncapsulateUnknown() {
        Refactoring refactoring = refactoringTestBuilder.build();

        refactoringService.addRefactoring(refactoring);

        String commitMessage = refactoringService.getCommitMessage();
        assertEquals("Encapsulate fields", commitMessage);
    }

    @Test
    void testExtractDelegate() {
        Refactoring refactoring = refactoringTestBuilder
                .beforeName("aString")
                .beforeType(CodeElementType.FIELD)
                .afterName("getaString")
                .afterType(CodeElementType.METHOD)
                .build();

        refactoringService.addRefactoring(refactoring);

        String commitMessage = refactoringService.getCommitMessage();
        assertEquals("Encapsulate field 'aString'", commitMessage);
    }
}
