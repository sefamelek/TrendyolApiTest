package com.trendyol;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)



@CucumberOptions(features = "src/test/java/com/trendyol/feature")


public class TestRunner {
}