package demo.hexagon.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "demo.hexagon")
public class ArchUnitRulesTest {

    @ArchTest
    static final ArchRule domain_shouldAccessOnlyProjectSpecificDomainClasses_AndOwnLibraries =
            classes().that()
                    .resideInAnyPackage("..domain..")
                    .should()
                    .onlyHaveDependentClassesThat()
                    .resideInAnyPackage("java..", "demo.hexagon..");

    @ArchTest
    static final ArchRule repositories_shouldBeInProperPackage_AndImplementPort =
            classes().that()
                    .areAnnotatedWith(Repository.class)
                    .should()
                    .bePackagePrivate()
                    .andShould()
                    .implement(resideInAPackage("..domain.repository.."))
                    .andShould()
                    .resideInAPackage("..adapter.persistence..")
                    .andShould()
                    .haveSimpleNameEndingWith("Adapter");

    @ArchTest
    static final ArchRule controllers_shouldBeInProperPackage =
            classes().that()
                    .areAnnotatedWith(RestController.class)
                    .should()
                    .resideInAPackage("..adapter.web.rest..")
                    .andShould()
                    .bePackagePrivate()
                    .andShould()
                    .haveSimpleNameEndingWith("Adapter");

    @ArchTest
    static final ArchRule services_shouldBeInProperPackage_AndImplementUseCases_AndCallPorts=
            classes().that()
                    .areAnnotatedWith(Service.class)
                    .should()
                    .haveSimpleNameEndingWith("Service")
                    .andShould()
                    .bePackagePrivate()
                    .andShould()
                    .resideInAnyPackage("..application.service..")
                    .andShould()
                    .implement(resideInAPackage("..application.port.."));

    @ArchTest
    static final ArchRule ports_shouldBeInProperPackage_AndShouldBeInterfaces =
            classes().that()
                    .resideInAPackage("..application.port")
                    .should()
                    .beInterfaces();
}
