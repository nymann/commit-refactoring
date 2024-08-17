package com.github.nymann.commitrefactoring.messages.move;

import com.github.nymann.commitrefactoring.*;
import junit.framework.TestCase;

public class MoveClassToPackageTest extends TestCase {

    public void testCreate() {
        CodeElement from = new CodeElement("Test", CodeElementType.CLASS);
        CodeElement to = new CodeElement("package", CodeElementType.PACKAGE);
        Refactoring refactoring = new Refactoring(RefactoringType.MOVE, from, to);
        RefactoringService refactoringService = new RefactoringService();
        refactoringService.addRefactoring(refactoring);
        String actual = refactoringService.getCommitMessage();
        assertEquals("Move class 'Test' to 'package'", actual);
    }
}