package io.github.tomaslad.maven.plugin.myversion.git;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GitDescribe {

    String tagName;
    int commitDistance;
    String commitAbbrev;
    boolean dirty;

    // HEAD commit je soucastne i otagovany commit
    public boolean isReleaseCommit() {
        return commitDistance == 0;
    }

}
