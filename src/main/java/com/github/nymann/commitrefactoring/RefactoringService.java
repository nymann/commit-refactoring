package com.github.nymann.commitrefactoring;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class RefactoringService {
    private final List<Refactoring> refactorings = new ArrayList<>();
    private final Deque<Refactoring> undoStack = new ArrayDeque<>();
    private final Deque<Refactoring> redoStack = new ArrayDeque<>();

    public void addRefactoring(Refactoring refactoring) {
        this.refactorings.add(refactoring);
        undoStack.push(refactoring);
        redoStack.clear();
    }

    public List<Refactoring> getRefactorings() {
        return new ArrayList<>(refactorings);
    }

    public void clearRefactorings() {
        refactorings.clear();
    }

    public void undoLastRefactoring() {
        if (undoStack.isEmpty()) {
            return;
        }
        if (refactorings.isEmpty()) {
            return;
        }
        Refactoring lastRefactoring = undoStack.pop();
        refactorings.remove(lastRefactoring);
        redoStack.push(lastRefactoring);
    }

    public void redoLastRefactoring() {
        if (!redoStack.isEmpty()) {
            Refactoring refactoring = redoStack.pop();
            refactorings.add(refactoring);
            undoStack.push(refactoring);
        }
    }

    public String getCommitMessage() {
        if (refactorings.isEmpty()) {
            return "UNSAFE";
        }
        if (refactorings.size() == 1) {
            Refactoring refactoring = refactorings.get(0);
            return CommitMessageFactory.create(refactoring).getMessage();
        }
        StringBuilder result = new StringBuilder();
        for (Refactoring refactoring : refactorings) {
            CommitMessage commitMessage = CommitMessageFactory.create(refactoring);
            result.append(commitMessage.getMessage()).append("\n");
        }
        return result.toString();
    }
}
