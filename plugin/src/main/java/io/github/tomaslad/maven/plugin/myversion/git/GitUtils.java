package io.github.tomaslad.maven.plugin.myversion.git;

import org.buildobjects.process.ProcBuilder;
import org.buildobjects.process.ProcResult;

public class GitUtils {

    public GitCommit getLastestTag() {
        ProcResult result = new ProcBuilder("git")
                .withArgs("describe", "--tags", "--always", "--first-parent", "--match=v*")
                .run();

        String output = result.getOutputString();


    }
}
