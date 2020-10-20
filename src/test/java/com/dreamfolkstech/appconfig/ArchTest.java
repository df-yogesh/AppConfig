package com.dreamfolkstech.appconfig;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.dreamfolkstech.appconfig");

        noClasses()
            .that()
                .resideInAnyPackage("com.dreamfolkstech.appconfig.service..")
            .or()
                .resideInAnyPackage("com.dreamfolkstech.appconfig.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.dreamfolkstech.appconfig.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
