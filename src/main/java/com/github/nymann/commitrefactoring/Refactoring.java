package com.github.nymann.commitrefactoring;

import com.intellij.psi.PsiElement;
import com.intellij.refactoring.listeners.RefactoringEventData;

import java.util.ArrayList;
import java.util.List;

import static com.intellij.refactoring.listeners.RefactoringEventData.PSI_ELEMENT_ARRAY_KEY;
import static com.intellij.refactoring.listeners.RefactoringEventData.PSI_ELEMENT_KEY;

public class Refactoring {
    private final String refactoringId;
    private List<PsiElement> before;
    private List<PsiElement> after;

    public Refactoring(String refactoringId) {
        this.refactoringId = refactoringId;
    }

    public Refactoring(String refactoringId, PsiElement before, PsiElement after) {
        this.refactoringId = refactoringId;
        this.before = List.of(before);
        this.after = List.of(after);
    }

    public Refactoring(String refactoringId, PsiElement before) {
        this.refactoringId = refactoringId;
        this.before = List.of(before.copy());
    }

    public Refactoring(String refactoringId, RefactoringEventData before) {
        this.refactoringId = refactoringId;
        PsiElement psiElement = before.get().get(PSI_ELEMENT_KEY);
        if (psiElement != null) {
            this.before = List.of(psiElement.copy());
            return;
        }
        PsiElement[] psiElements = before.get().get(PSI_ELEMENT_ARRAY_KEY);
        this.before = new ArrayList<>();
        if (psiElements != null) {
            for (PsiElement element : psiElements) {
                this.before.add(element.copy());
            }
        }
    }

    public List<PsiElement> getAfter() {
        return after;
    }

    public void setAfter(RefactoringEventData after) {
        if (after == null) {
            return;
        }
        PsiElement psiElement = after.get().get(PSI_ELEMENT_KEY);
        if (psiElement != null) {
            this.after = List.of(psiElement);
            return;
        }
        PsiElement[] psiElements = after.get().get(PSI_ELEMENT_ARRAY_KEY);
        if (psiElements != null) {
            this.after = List.of(psiElements);
        }
    }

    public List<PsiElement> getBefore() {
        return before;
    }

    public PsiElement getFirstAfter() {
        if (after == null || after.isEmpty()) {
            return null;
        }
        return after.get(0);
    }

    public PsiElement getFirstBefore() {
        if (before == null || before.isEmpty()) {
            return null;
        }
        return before.get(0);
    }

    public String getRefactoringId() {
        return refactoringId;
    }
}
