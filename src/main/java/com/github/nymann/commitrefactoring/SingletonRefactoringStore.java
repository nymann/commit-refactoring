package com.github.nymann.commitrefactoring;

import com.intellij.openapi.project.Project;

import java.util.*;
import java.util.logging.Logger;

public class SingletonRefactoringStore {
    private static final Logger log = Logger.getLogger(SingletonRefactoringStore.class.getName());

    private static final Map<Project, SingletonRefactoringStore> instances = new HashMap<>();

    private final List<Refactoring> refactoring = new ArrayList<>();
    private final Deque<Refactoring> undoStack = new ArrayDeque<>();
    private final Deque<Refactoring> redoStack = new ArrayDeque<>();

    private SingletonRefactoringStore() {
        // Private constructor to prevent instantiation
    }

    public static SingletonRefactoringStore getInstance(Project project) {
        return instances.computeIfAbsent(project, k -> new SingletonRefactoringStore());
    }

    public void addRefactoring(Refactoring refactoring) {
        this.refactoring.add(refactoring);
        undoStack.push(refactoring);
        redoStack.clear();
    }

    public List<Refactoring> getRefactorings() {
        return new ArrayList<>(refactoring);
    }

    public void clear() {
        refactoring.clear();
    }

    public void undoLastRefactoring() {
        if (undoStack.isEmpty()) {
            return;
        }
        if (refactoring.isEmpty()) {
            // TODO: We have just committed. The user should revert their commit instead
            // Example:
            // Inline Method
            // Commit
            // Undo Last Refactoring
            // We Should then either not support it or add the reverse refactoring "Extract Method"
            log.warning("User just committed and want to undo last refactoring, revert your commit instead.");
            return;
        }
        Refactoring lastRefactoring = undoStack.pop();
        refactoring.remove(lastRefactoring);
        redoStack.push(lastRefactoring);
    }

    public void redoLastRefactoring() {
        if (!redoStack.isEmpty()) {
            Refactoring lastUndoneRefactoring = redoStack.pop();
            refactoring.add(lastUndoneRefactoring);
            undoStack.push(lastUndoneRefactoring);
        }
    }

}
