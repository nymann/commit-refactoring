package com.github.nymann.commitrefactoring.domain;

public record CodeElement(String name, CodeElementType type) {

    @Override
    public String toString() {
        return "CodeElement{name='" + name + "', type=" + type + "}";
    }
}
