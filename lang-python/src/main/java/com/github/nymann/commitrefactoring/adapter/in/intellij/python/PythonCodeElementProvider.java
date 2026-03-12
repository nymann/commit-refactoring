package com.github.nymann.commitrefactoring.adapter.in.intellij.python;

import com.github.nymann.commitrefactoring.adapter.in.intellij.CodeElementProvider;
import com.github.nymann.commitrefactoring.adapter.in.intellij.PsiElementFactory;
import com.github.nymann.commitrefactoring.domain.CodeElement;
import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.refactoring.listeners.RefactoringEventData;
import com.jetbrains.python.psi.PyAssignmentStatement;
import com.jetbrains.python.psi.PyCallExpression;
import com.jetbrains.python.psi.PyClass;
import com.jetbrains.python.psi.PyExpressionStatement;
import com.jetbrains.python.psi.PyFunction;
import com.jetbrains.python.psi.PyNamedParameter;
import com.jetbrains.python.psi.PyReferenceExpression;
import com.jetbrains.python.psi.PyTargetExpression;

import static java.util.Optional.ofNullable;

public class PythonCodeElementProvider implements CodeElementProvider {
    private static final Logger logger = Logger.getInstance(PythonCodeElementProvider.class);

    @Override
    public CodeElement create(RefactoringEventData eventData) {
        PsiElement element = PsiElementFactory.create(eventData);
        if (element instanceof PyClass pyClass) {
            return new CodeElement(pyClass.getName(), CodeElementType.CLASS);
        }
        if (element instanceof PyFunction pyFunction) {
            return pyFunctionElement(pyFunction);
        }
        if (element instanceof PyTargetExpression pyTarget) {
            return pyTargetElement(pyTarget);
        }
        if (element instanceof PyAssignmentStatement assignment) {
            for (var target : assignment.getTargets()) {
                if (target instanceof PyTargetExpression pyTarget) {
                    return pyTargetElement(pyTarget);
                }
            }
            String name = assignment.getTargets().length > 0 ? assignment.getTargets()[0].getText() : "unknown";
            return new CodeElement(name, CodeElementType.LOCAL_VARIABLE);
        }
        if (element instanceof PyExpressionStatement exprStmt) {
            var expr = exprStmt.getExpression();
            if (expr instanceof PyCallExpression call
                    && call.getCallee() instanceof PyReferenceExpression ref) {
                return new CodeElement(ref.getName(), CodeElementType.METHOD);
            }
            if (expr instanceof PyReferenceExpression ref) {
                return new CodeElement(ref.getName(), CodeElementType.METHOD);
            }
            return new CodeElement(expr.getText(), CodeElementType.UNKNOWN);
        }
        if (element instanceof PyNamedParameter pyParam) {
            return new CodeElement(pyParam.getName(), CodeElementType.PARAMETER);
        }
        String unsupportedClassName = ofNullable(element)
                .map(Object::getClass)
                .map(Class::getName)
                .orElse("null");
        logger.warn(unsupportedClassName + " is unsupported");
        return new CodeElement(unsupportedClassName, CodeElementType.UNKNOWN);
    }

    private static CodeElement pyFunctionElement(PyFunction pyFunction) {
        if (isInitMethod(pyFunction)) {
            return new CodeElement(pyFunction.getName(), CodeElementType.CONSTRUCTOR);
        }
        return new CodeElement(pyFunction.getName(), CodeElementType.METHOD);
    }

    private static CodeElement pyTargetElement(PyTargetExpression pyTarget) {
        PsiElement parent = pyTarget.getParent();
        if (parent instanceof PyFunction) {
            return new CodeElement(pyTarget.getName(), CodeElementType.LOCAL_VARIABLE);
        }
        if (parent != null && parent.getParent() instanceof PyClass) {
            return new CodeElement(pyTarget.getName(), CodeElementType.FIELD);
        }
        return new CodeElement(pyTarget.getName(), CodeElementType.LOCAL_VARIABLE);
    }

    private static boolean isInitMethod(PyFunction pyFunction) {
        return "__init__".equals(pyFunction.getName());
    }
}
