package com.github.nymann.commitrefactoring.intellij.listeners;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.RefactoringType;
import com.github.nymann.commitrefactoring.intellij.CodeElementFactory;
import com.github.nymann.commitrefactoring.intellij.IntelliJRefactoringService;
import com.github.nymann.commitrefactoring.intellij.PsiElementFactory;
import com.github.nymann.commitrefactoring.intellij.RefactoringTypeFactory;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.listeners.RefactoringEventData;
import com.intellij.refactoring.listeners.RefactoringEventListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RefactoringListener implements RefactoringEventListener {
    private static final Logger logger = Logger.getInstance(RefactoringListener.class);
    private final IntelliJRefactoringService refactoringService;
    private PsiElement before = null;

    public RefactoringListener(Project project) {
        this.refactoringService = project.getService(IntelliJRefactoringService.class);
    }

    @Override
    public void refactoringStarted(@NotNull String refactoringId, @Nullable RefactoringEventData beforeData) {
        before = PsiElementFactory.create(beforeData);
    }

    @Override
    public void refactoringDone(@NotNull String refactoringId, @Nullable RefactoringEventData refactoringEventData) {
        PsiElement after = PsiElementFactory.create(refactoringEventData);
        RefactoringType refactoringType = RefactoringTypeFactory.fromIntellij(refactoringId);
        if (RefactoringType.UNKNOWN.equals(refactoringType)) {
            logUnsupported(refactoringId, after);
            return;
        }
        Refactoring refactoring = new Refactoring(refactoringType, CodeElementFactory.create(before), CodeElementFactory.create(after));
        refactoringService.addRefactoring(refactoring);
        refactoringService.setCommitMessageOnPanel();
    }

    private void logUnsupported(@NotNull String refactoringId, PsiElement after) {
        String beforeClass = "UNKNOWN";
        if (before != null) {
            beforeClass = before
                    .getClass()
                    .getName();
        }
        String afterClass = "UNKNOWN";
        if (after != null) {
            afterClass = after
                    .getClass()
                    .getName();
        }
        logger.warn(refactoringId + " is unsupported. before '" + beforeClass + "' after '" + afterClass + "'");
    }

    @Override
    public void conflictsDetected(@NotNull String refactoringId, @NotNull RefactoringEventData conflictsData) {
        CodeElement element = CodeElementFactory.create(PsiElementFactory.create(conflictsData));
        logger.warn("Conflict detected: '" + refactoringId + "', data: " + element);
    }

    @Override
    public void undoRefactoring(@NotNull String refactoringId) {
        refactoringService.undoLastRefactoring();
    }

    @Override
    public void redoRefactoring(@NotNull String refactoringId) {
        refactoringService.redoLastRefactoring();
    }

}
