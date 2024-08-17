package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ExtractCommitMessageTest {

    private String getExtractCommitMessage(CodeElementType method) {
        CodeElement codeElement = new CodeElement("test", method);
        RefactoringService refactoringService = new RefactoringService();
        Refactoring refactoring = new Refactoring(RefactoringType.EXTRACT, null, codeElement);
        refactoringService.addRefactoring(refactoring);
        return refactoringService.getCommitMessage();
    }

    @Test
    public void testExtractMethod() {
        String actual = getExtractCommitMessage(CodeElementType.METHOD);

        assertEquals("Extract method 'test'", actual);
    }


    @Test
    public void testUnknownExtractMethod() {
        String actual = getExtractCommitMessage(CodeElementType.UNKNOWN);

        assertEquals(actual, "Extract");
    }

    @Test
    public void testExtractVariable() {
        String actual = getExtractCommitMessage(CodeElementType.LOCAL_VARIABLE);

        assertEquals(actual, "Extract variable 'test'");
    }
}