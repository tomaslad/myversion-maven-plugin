package io.github.tomaslad.maven.plugin.myversion.git;

import org.buildobjects.process.ProcBuilder;
import org.buildobjects.process.ProcResult;

public class GitUtils {

    public String getLastestTag() {
        ProcResult result = new ProcBuilder("git")
                .withArgs("describe", "--tags", "--always", "--first-parent")
                .run();
        return result.getOutputString();
    }

    public String getLastestTag() {
        ProcResult result = new ProcBuilder("git")
                .withArgs("describe", "--tags", "--always", "--first-parent")
                .run();
        return result.getOutputString();
    }
}
