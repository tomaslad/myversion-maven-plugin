package io.github.tomaslad.maven.plugin.myversion;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.buildobjects.process.ExternalProcessFailureException;
import org.buildobjects.process.ProcBuilder;
import org.buildobjects.process.ProcResult;

@Slf4j
@UtilityClass
public class GitUtils {

    public static void readLatestTag() {
        ProcResult result = new ProcBuilder("git describe") // https://git-scm.com/docs/git-describe
                .withArg("--tags")
                .withArg("--always")
                .withArg("--first-parent")
                .withArg("--match=v*")
                .withArg("--abbrev=0")
                .withArg("--dirty")
                .run();

        String output = result.getOutputString();


    }

    public static void writeTag(String version) {
        ProcBuilder procBuilder = new ProcBuilder("git tag") // https://git-scm.com/docs/git-tag
                .withArg("--annotate " + version)
                .withArg("--message Release " + version);
        try {
            procBuilder.run();
        } catch (ExternalProcessFailureException e) {
            log.error("Set tag error: ", e);
        }
    }
}
