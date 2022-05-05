package io.github.tomaslad.maven.plugin.myversion;

import org.apache.commons.lang3.StringUtils;

public class GitDescribe {

    private final String version;
    private final int commitDistance;
    private final String commitAbbrev;
    private final boolean dirty;

    public GitDescribe(String version, int commitDistance, String commitAbbrev, boolean dirty) {
        this.version = version;
        this.commitDistance = commitDistance;
        this.commitAbbrev = commitAbbrev;
        this.dirty = dirty;
    }

    public String getVersion() {
        return version;
    }

    public int getCommitDistance() {
        return commitDistance;
    }

    public String getCommitAbbrev() {
        return commitAbbrev;
    }

    public boolean isDirty() {
        return dirty;
    }

    // That means it contains a tag
    public boolean isReleaseCommit() {
        return commitDistance == 0;
    }

    public static GitDescribe parse(String output) {
        if (StringUtils.isEmpty(output)) {
            throw new IllegalArgumentException("describe string is null or empty.");
        }

        StringBuilder version = null;
        int commitDistance = 0;
        String commitAbbrev = null;
        boolean dirty = false;

        // v1.0.0-rc.1-dirty soucastny commit je otagovan
        // v1.0.0-rc.1-5-g5cd2f15-dirty tag je na nejakem drivejsim commitu
        String[] parts = output.split("-");

        for (String part : parts) {
            if (part.startsWith("v")) {
                version = new StringBuilder(part);
            } else if (part.startsWith("rc")) {
                if (version == null) {
                    throw new IllegalStateException("Release candidate part should go after vX.Y.Z part but never before.");
                }
                version.append(part);
            } else if (part.matches("[1-9]+")) {
                commitDistance = Integer.parseInt(part);
            } else if (part.startsWith("g") && part.length() == 8) {
                // g + commit abbrev (seven letters by default)
                commitAbbrev = part.substring(1);
            } else if (part.equals("-dirty")) {
                // dirty
                dirty = true;
            }
        }

        return new GitDescribe(version.toString(), commitDistance, commitAbbrev, dirty);

    }

}
