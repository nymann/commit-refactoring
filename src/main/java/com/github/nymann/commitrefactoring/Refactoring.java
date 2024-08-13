package com.github.nymann.commitrefactoring;

import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.listeners.RefactoringEventData;

import java.util.Objects;

import static com.intellij.refactoring.listeners.RefactoringEventData.PSI_ELEMENT_KEY;

public class Refactoring {
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
        // Could also be get(PSI_ELEMENT_ARRAY_KEY)
        if(psiElement != null) {
            this.before = psiElement.copy();
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
