package nl.pancompany.unicorn.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
public class ArchitectureTest {

    @Test
    void domainDoesNotDependOnAdapters() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.pancompany.unicorn");

        noClasses()
                .that()
                .resideInAPackage("nl.pancompany.unicorn.application..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("nl.pancompany.unicorn.adapter..")
                .check(importedClasses);
    }
}
