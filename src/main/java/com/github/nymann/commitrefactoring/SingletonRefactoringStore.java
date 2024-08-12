package com.github.nymann.commitrefactoring;

import com.intellij.psi.PsiElement;
import com.intellij.refactoring.listeners.RefactoringEventData;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.logging.Logger;

public class SingletonRefactoringStore {
    private static final Logger log = Logger.getLogger(SingletonRefactoringStore.class.getName());

    private static final SingletonRefactoringStore INSTANCE = new SingletonRefactoringStore();

    private final List<Refactoring> refactorings = new ArrayList<>();
    private final Deque<Refactoring> undoStack = new ArrayDeque<>();
    private final Deque<Refactoring> redoStack = new ArrayDeque<>();

    private SingletonRefactoringStore() {
        // Private constructor to prevent instantiation
    }

    public static SingletonRefactoringStore getInstance() {
        return INSTANCE;
    }

    public void addRefactoring(String refactoringId, RefactoringEventData afterData) {
        Refactoring refactoring = new Refactoring(refactoringId, constructCommitMessage(refactoringId, afterData));
        refactorings.add(refactoring);
        undoStack.push(refactoring);
        redoStack.clear();
    }

    public List<Refactoring> getRefactorings() {
        return new ArrayList<>(refactorings);
    }

    private String constructCommitMessage(String refactoringId, RefactoringEventData afterData) {
        String replace = refactoringId.replaceFirst("refactoring\\.", "").replace(".", " ");
        String result = "Refactoring: " + replace.toLowerCase();
        PsiElement psiElement = afterData != null ? afterData.getUserData(RefactoringEventData.PSI_ELEMENT_KEY) : null;
        String methodName = psiElement != null ? psiElement.toString().split(":")[psiElement.toString().split(":").length - 1].trim() : null;
        if (methodName != null) {
            return result + " " + methodName;
        }
        return result;
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
            log.warning("User just committed and want to undo last refactoring, revert your commit instead.");
            return;
        }
        Refactoring lastRefactoring = undoStack.pop();
        refactorings.remove(lastRefactoring);
        redoStack.push(lastRefactoring);
    }

    public void redoLastRefactoring() {
        if (!redoStack.isEmpty()) {
            Refactoring lastUndoneRefactoring = redoStack.pop();
            refactorings.add(lastUndoneRefactoring);
            undoStack.push(lastUndoneRefactoring);
        }
    }

}
