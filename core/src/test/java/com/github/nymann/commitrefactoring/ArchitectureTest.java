package com.github.nymann.commitrefactoring;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchitectureTest {

    private static JavaClasses importedClasses;

    @BeforeAll
    static void setup() {
        importedClasses = new ClassFileImporter()
                .importPath(Path.of("build/classes/java/main"));
    }

    @Test
    void allClassesShouldResideInDomainApplicationOrAdapter() {
        classes()
                .should().resideInAnyPackage(
                        "..domain..",
                        "..application..",
                        "..adapter.."
                )
                .check(importedClasses);
    }

    @Test
    void domainShouldNotDependOnApplicationOrAdapter() {
        noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAnyPackage("..application..", "..adapter..")
                .check(importedClasses);
    }

    @Test
    void applicationShouldNotDependOnAdapter() {
        noClasses()
                .that().resideInAPackage("..application..")
                .should().dependOnClassesThat().resideInAPackage("..adapter..")
                .check(importedClasses);
    }

    @Test
    void domainShouldNotUseIntellijImports() {
        noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("com.intellij..")
                .check(importedClasses);
    }

    @Test
    void adapterOutShouldNotDependOnAdapterIn() {
        noClasses()
                .that().resideInAPackage("..adapter.out..")
                .should().dependOnClassesThat().resideInAPackage("..adapter.in..")
                .check(importedClasses);
    }

    @Test
    void applicationShouldNotUseIntellijImports() {
        noClasses()
                .that().resideInAPackage("..application..")
                .should().dependOnClassesThat().resideInAPackage("com.intellij..")
                .check(importedClasses);
    }
}
