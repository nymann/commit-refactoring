package com.github.nymann.commitrefactoring;

import java.util.Objects;

public final class Refactoring {
    private final String id;
    private final String description;

    public Refactoring(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String id() {
        return id;
    }

    public String description() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Refactoring) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "Refactoring[" +
                "id=" + id + ", " +
                "description=" + description + ']';
    }

}
