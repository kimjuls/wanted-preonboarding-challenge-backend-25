package com.wanted.clone.oneport;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = "com.wanted.clone.oneport")
public class Arch2Tests {
    @ArchTest
    public static final ArchRule controllerRuleTest = classes().that().resideInAPackage("..presentation.web")
        .should().beAnnotatedWith(RestController.class)
        .orShould().beAnnotatedWith(Controller.class)
        .orShould().haveSimpleNameContaining("Controller");

    @ArchTest
    public static final ArchRule portAndAdapter = onionArchitecture()
        .domainModels("com.wanted.clone.oneport.payments.domain.entity")
        .domainServices("com.wanted.clone.oneport.payments.application.service")
        .applicationServices("com.wanted.clone.oneport.core.config..")
        .adapter("persistence", "com.wanted.clone.oneport.payment.application.port.out.repository..")
        .adapter("rest", "com.wanted.clone.oneport.payment.presentation.web..");

}
