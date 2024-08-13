package com.github.nymann.commitrefactoring;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;

import java.util.*;

public class RefactoringStore {
    private static final Logger log = Logger.getInstance(RefactoringStore.class);

    private static final Map<String, RefactoringStore> instances = new HashMap<>();

    private final List<Refactoring> refactorings = new ArrayList<>();
    private final Deque<Refactoring> undoStack = new ArrayDeque<>();
    private final Deque<Refactoring> redoStack = new ArrayDeque<>();

    private RefactoringStore() {
        // Private constructor to prevent instantiation
    }

    public static RefactoringStore getInstance(Project project) {
        return RefactoringStore.getInstance(project.getLocationHash());
    }

    public static RefactoringStore getInstance(String projectId) {
        return instances.computeIfAbsent(projectId, k -> new RefactoringStore());
    }

    public void addRefactoring(Refactoring refactoring) {
        this.refactorings.add(refactoring);
        undoStack.push(refactoring);
        redoStack.clear();
    }

    public List<Refactoring> getRefactorings() {
        return new ArrayList<>(refactorings);
    }

    public void clear() {
        refactorings.clear();
    }

    public void undoLastRefactoring() {
        if (undoStack.isEmpty()) {
            return;
        }
        if (refactorings.isEmpty()) {
            // TODO: We have just committed. The user should revert their commit instead
            // Example:
            // Inline Method
            // Commit
            // Undo Last Refactoring
            // We Should then either not support it or add the reverse refactoring "Extract Method"
            log.warn("User just committed and want to undo last refactoring, revert your commit instead.");
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

}
