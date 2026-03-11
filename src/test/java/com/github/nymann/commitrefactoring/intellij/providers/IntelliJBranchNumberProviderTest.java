package com.github.nymann.commitrefactoring.intellij.providers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntelliJBranchNumberProviderTest {
    @Test
    void test() {
        // when
        String actual = IntelliJBranchNumberProvider.filterNumbers("abc12321def123");

        // then
        assertEquals("12321", actual);
    }
}
