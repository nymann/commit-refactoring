package com.github.nymann.commitrefactoring;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RefactoringStoreTest {

    @Test
    public void testUndoLastRefactoring() {
        RefactoringStore store = RefactoringStore.getInstance("testUndoLastRefactoring");
        Refactoring refactoring = new Refactoring("");
        store.addRefactoring(refactoring);
        store.undoLastRefactoring();
        assertEquals(0, store.getRefactorings().size());
    }

    @Test
    public void testRedoRefactoring() {
        RefactoringStore store = RefactoringStore.getInstance("testRedoRefactoring");
        Refactoring refactoring = new Refactoring("");
        store.addRefactoring(refactoring);
        store.undoLastRefactoring();
        store.redoLastRefactoring();
        assertEquals(1, store.getRefactorings().size());
    }

    @Test
    public void testMultipleRefactorings() {
        RefactoringStore store = RefactoringStore.getInstance("testMultipleRefactorings");
        Refactoring refactoring1 = new Refactoring("refactor1");
        Refactoring refactoring2 = new Refactoring("refactor2");

        store.addRefactoring(refactoring1);
        store.addRefactoring(refactoring2);

        assertEquals(2, store.getRefactorings().size());

        store.undoLastRefactoring();
        assertEquals(1, store.getRefactorings().size());

        store.redoLastRefactoring();
        assertEquals(2, store.getRefactorings().size());
    }
}