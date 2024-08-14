package com.github.nymann.commitrefactoring;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RefactoringStoreTest {
    private Refactoring emptyRequest(String refactoringId) {
        CodeElement test = new CodeElement("test", CodeElementType.UNKNOWN);
        return new Refactoring(refactoringId, test, test);
    }
    private Refactoring emptyRequest() {
        CodeElement test = new CodeElement("test", CodeElementType.UNKNOWN);
        return new Refactoring("", test, test);
    }

    @Test
    public void testUndoLastRefactoring() {
        RefactoringStore store = RefactoringStore.getInstance("testUndoLastRefactoring");
        store.addRefactoring(emptyRequest());
        store.undoLastRefactoring();
        assertEquals(0, store.getRefactorings().size());
    }

    @Test
    public void testRedoRefactoring() {
        RefactoringStore store = RefactoringStore.getInstance("testRedoRefactoring");
        store.addRefactoring(emptyRequest());
        store.undoLastRefactoring();
        store.redoLastRefactoring();
        assertEquals(1, store.getRefactorings().size());
    }

    @Test
    public void testMultipleRefactorings() {
        RefactoringStore store = RefactoringStore.getInstance("testMultipleRefactorings");
        Refactoring refactoring1 = emptyRequest("refactor1");
        Refactoring refactoring2 = emptyRequest("refactor2");

        store.addRefactoring(refactoring1);
        store.addRefactoring(refactoring2);

        assertEquals(2, store.getRefactorings().size());

        store.undoLastRefactoring();
        assertEquals(1, store.getRefactorings().size());

        store.redoLastRefactoring();
        assertEquals(2, store.getRefactorings().size());
    }
}
