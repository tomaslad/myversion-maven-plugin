package io.github.tomaslad.maven.plugin.myversion;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.buildobjects.process.ExternalProcessFailureException;
import org.buildobjects.process.ProcBuilder;

import java.io.IOException;

@Slf4j
@UtilityClass
public class GitUtils {

    public static void readLatestTag() {
        ProcBuilder procBuilder = new ProcBuilder("git describe") // https://git-scm.com/docs/git-describe
                .withArg("--tags")
                .withArg("--always")
                .withArg("--first-parent")
                .withArg("--match=v*")
                .withArg("--abbrev=0")
                .withArg("--dirty");

        try {
            procBuilder.run();
        } catch (ExternalProcessFailureException e) {
            log.error("Read latest tag error: ", e);
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
