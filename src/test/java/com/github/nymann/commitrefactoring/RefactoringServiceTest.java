package com.github.nymann.commitrefactoring;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RefactoringServiceTest {
    private Refactoring emptyRequest() {
        CodeElement test = new CodeElement("test", CodeElementType.UNKNOWN);
        return new Refactoring(RefactoringType.None, test, test);
    }

    @Test
    public void testUndoLastRefactoring() {
        RefactoringService store = new RefactoringService();
        store.addRefactoring(emptyRequest());
        store.undoLastRefactoring();
        assertEquals(0, store.getRefactorings().size());
    }

    @Test
    public void testRedoRefactoring() {
        RefactoringService store = new RefactoringService();
        store.addRefactoring(emptyRequest());
        store.undoLastRefactoring();
        store.redoLastRefactoring();
        assertEquals(1, store.getRefactorings().size());
    }

    @Test
    public void testMultipleRefactorings() {
        RefactoringService store = new RefactoringService();
        Refactoring refactoring1 = emptyRequest();
        Refactoring refactoring2 = emptyRequest();

        store.addRefactoring(refactoring1);
        store.addRefactoring(refactoring2);

        assertEquals(2, store.getRefactorings().size());

        store.undoLastRefactoring();
        assertEquals(1, store.getRefactorings().size());

        store.redoLastRefactoring();
        assertEquals(2, store.getRefactorings().size());
    }
}
