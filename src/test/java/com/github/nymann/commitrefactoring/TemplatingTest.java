package com.github.nymann.commitrefactoring;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplatingTest {
    private RefactoringTestBuilder refactoringTestBuilder;

    @BeforeEach
    void setUp() {
        refactoringTestBuilder = new RefactoringTestBuilder().refactoringType(RefactoringType.INLINE);
    }

    @Test
    public void testTemplate() {
        List<TemplateVariableProvider> providers = List.of(new RefactoringProvider());
        TemplateProcessor templateProcessor = new TemplateProcessor("TICKET-123: r - ${refactoring}", providers);
        RefactoringService refactoringService = new RefactoringService(templateProcessor);
        Refactoring refactoring = refactoringTestBuilder
                .beforeName("foo")
                .beforeType(CodeElementType.LOCAL_VARIABLE)
                .build();

        refactoringService.addRefactoring(refactoring);

        assertEquals("TICKET-123: r - Inline variable 'foo'", refactoringService.getCommitMessage());
    }
}