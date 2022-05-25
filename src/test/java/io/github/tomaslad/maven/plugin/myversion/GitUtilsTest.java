package io.github.tomaslad.maven.plugin.myversion;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class GitUtilsTest {

    @Test
    public void testWithCheck() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("mvn.cmd -f ./src/test/resources/test-repository/pom.xml :io.github.tomaslad:myversion-maven-plugin:bump-major");
        int exitValue = process.waitFor();
        if (exitValue != 0) {
            log.warn("exitValue: {}", exitValue);
        }
        assertTrue(IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8).contains("Repository has uncommited changes"));
    }

    @Test
    public void testWithoutCheck() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("mvn.cmd -f ./src/test/resources/nocheck-test-repository/pom.xml :io.github.tomaslad:myversion-maven-plugin:bump-major");
        int exitValue = process.waitFor();
        if (exitValue != 0) {
            log.warn("exitValue: {}", exitValue);
        }
        assertTrue(IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8).contains("Bump version from"));
    }
}