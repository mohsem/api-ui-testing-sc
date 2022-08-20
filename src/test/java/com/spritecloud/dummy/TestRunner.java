package com.spritecloud.dummy;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/spritecloud/dummy/features",
        glue = "com.spritecloud.dummy.step_definitions",
        plugin = {"pretty", "html:report/cucumber-report.html" , "junit:report/cucumber-results.xml"},
        publish = true
)

public class TestRunner {
}
