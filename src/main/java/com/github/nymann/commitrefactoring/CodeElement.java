package com.github.nymann.commitrefactoring;

public class CodeElement {
    private final String name;
    private final CodeElementType type;

    public CodeElement(String name, CodeElementType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public CodeElementType getType() {
        return type;
    }
}
