package com.github.nymann.commitrefactoring.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class RefactoringHistory {
    private final List<Refactoring> refactorings = new ArrayList<>();
    private final Deque<Refactoring> undoStack = new ArrayDeque<>();
    private final Deque<Refactoring> redoStack = new ArrayDeque<>();

    public void add(Refactoring refactoring) {
        refactorings.add(refactoring);
        undoStack.push(refactoring);
        redoStack.clear();
    }

    public void undo() {
        if (undoStack.isEmpty() || refactorings.isEmpty()) {
            return;
        }
        Refactoring lastRefactoring = undoStack.pop();
        refactorings.remove(lastRefactoring);
        redoStack.push(lastRefactoring);
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Refactoring refactoring = redoStack.pop();
            refactorings.add(refactoring);
            undoStack.push(refactoring);
        }
    }

    public void clear() {
        refactorings.clear();
    }

    public String composeMessage(Function<Refactoring, String> formatter, Supplier<String> defaultMessage) {
        String message = refactorings.stream()
                .map(formatter)
                .collect(Collectors.joining("\n"));
        return message.isEmpty() ? defaultMessage.get() : message;
    }
}
