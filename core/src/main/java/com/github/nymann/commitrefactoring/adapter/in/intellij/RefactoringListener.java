package com.github.nymann.commitrefactoring.adapter.in.intellij;

import com.github.nymann.commitrefactoring.domain.CodeElement;
import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.github.nymann.commitrefactoring.domain.Refactoring;
import com.github.nymann.commitrefactoring.domain.RefactoringType;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.refactoring.listeners.RefactoringEventData;
import com.intellij.refactoring.listeners.RefactoringEventListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RefactoringListener implements RefactoringEventListener {
    private static final Logger logger = Logger.getInstance(RefactoringListener.class);
    private final Project project;
    private final IntelliJRefactoringService refactoringService;
    private CodeElement before = null;

    public RefactoringListener(Project project) {
        this.project = project;
        this.refactoringService = project.getService(IntelliJRefactoringService.class);
    }

    @Override
    public void refactoringStarted(@NotNull String refactoringId, @Nullable RefactoringEventData beforeData) {
        before = CodeElementFactory.create(beforeData);
        if (before.type() == CodeElementType.UNKNOWN) {
            before = resolveFromCaret();
        }
        logger.info("Started: " + refactoringId + " before=" + before);
    }

    private CodeElement resolveFromCaret() {
        Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        if (editor == null) {
            return new CodeElement("UNKNOWN", CodeElementType.UNKNOWN);
        }
        PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
        if (psiFile == null) {
            return new CodeElement("UNKNOWN", CodeElementType.UNKNOWN);
        }
        int offset = editor.getCaretModel().getOffset();
        PsiElement element = psiFile.findElementAt(offset);
        while (element != null && !(element instanceof PsiNamedElement)) {
            element = element.getParent();
        }
        if (element instanceof PsiNamedElement named) {
            CodeElement result = CodeElementFactory.createFromPsiElement(element);
            if (result.type() != CodeElementType.UNKNOWN) {
                return result;
            }
            return new CodeElement(named.getName(), CodeElementType.METHOD);
        }
        return new CodeElement("UNKNOWN", CodeElementType.UNKNOWN);
    }

    @Override
    public void refactoringDone(@NotNull String refactoringId, @Nullable RefactoringEventData refactoringEventData) {
        CodeElement after = CodeElementFactory.create(refactoringEventData);
        RefactoringType refactoringType = RefactoringTypeFactory.fromIntellij(refactoringId);
        Refactoring refactoring = new Refactoring(refactoringType, before, after);
        if (!refactoring.isSupported()) {
            logger.warn("UNSUPPORTED: " + refactoringId + ": " + refactoring);
            return;
        }
        logger.info("Captured: " + refactoringId + " refactoring=" + refactoring);
        refactoringService.addRefactoring(refactoring);
        refactoringService.setCommitMessageOnPanel();
    }

    @Override
    public void conflictsDetected(@NotNull String refactoringId, @NotNull RefactoringEventData conflictsData) {
        CodeElement element = CodeElementFactory.create(conflictsData);
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
