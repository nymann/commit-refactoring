package com.github.nymann.commitrefactoring;

import com.intellij.psi.PsiElement;
import com.intellij.refactoring.listeners.RefactoringEventData;

import java.util.logging.Logger;

import static com.intellij.refactoring.listeners.RefactoringEventData.PSI_ELEMENT_ARRAY_KEY;
import static com.intellij.refactoring.listeners.RefactoringEventData.PSI_ELEMENT_KEY;

public class Refactoring {
    private static final Logger log = Logger.getLogger(Refactoring.class.getName());

    private final String refactoringId;
    private PsiElement before;
    private PsiElement after;

    public Refactoring(String refactoringId) {
        this.refactoringId = refactoringId;
    }

    public Refactoring(String refactoringId, PsiElement before, PsiElement after) {
        this.refactoringId = refactoringId;
        this.before = before;
        this.after = after;
    }

    public Refactoring(String refactoringId, PsiElement before) {
        this.refactoringId = refactoringId;
        this.before = before.copy();
    }

    public Refactoring(String refactoringId, RefactoringEventData before) {
        this.refactoringId = refactoringId;
        PsiElement psiElement = before.get().get(PSI_ELEMENT_KEY);
        if (psiElement != null) {
            this.before = psiElement.copy();
            return;
        }
        PsiElement[] psiElements = before.get().get(PSI_ELEMENT_ARRAY_KEY);
        if (psiElements != null) {
            log.warning("PSI_ELEMENT_ARRAY_KEY found, relevant? " + refactoringId);
        }
    }

    public PsiElement getAfter() {
        return after;
    }

    public void setAfter(PsiElement after) {
        this.after = after;
    }

    public void setAfter(RefactoringEventData after) {
        setAfter(after.get().get(PSI_ELEMENT_KEY));
    }

    public PsiElement getBefore() {
        return before;
    }

    public String getRefactoringId() {
        return refactoringId;
    }


}
