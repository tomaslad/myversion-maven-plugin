package io.github.tomaslad.maven.plugin.myversion;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.buildobjects.process.ExternalProcessFailureException;
import org.buildobjects.process.ProcBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@UtilityClass
public class GitUtils {

    public static String readLatestTag() {
        // https://git-scm.com/docs/git-describe



       // ProcessBuilder processBuilder = new ProcessBuilder().command("git describe --tags --always --first-parent --match=v* --abbrev=0 --dirty");
        try {
            Process process = Runtime.getRuntime().exec("git describe --tags --always --first-parent --match=v* --abbrev=0 --dirty");
           // Process process = processBuilder.start();
            int exitValue = process.waitFor();
            log.info("exitValue: {}", exitValue);
            return IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException | InterruptedException e) {
            log.error("Set tag error: ", e);
            throw new RuntimeException(e);
        }
    }

    public static void writeTag(String version) {
        ProcBuilder procBuilder = new ProcBuilder("git tag --annotate " + version + " --message Ahoj"); // https://git-scm.com/docs/git-tag
        try {
            procBuilder.run();
        } catch (ExternalProcessFailureException e) {
            log.error("Set tag error: ", e);
        }
    }

    public static void writeTag2(String version) {
        ProcessBuilder processBuilder = new ProcessBuilder().command("git tag --annotate " + version + " --message Ahoj");
        try {
            Process process = processBuilder.start();
            int exitValue = process.waitFor();
            log.info("exitValue: {}", exitValue);
        } catch (IOException | InterruptedException e) {
            log.error("Set tag error: ", e);
        }
    }
}
