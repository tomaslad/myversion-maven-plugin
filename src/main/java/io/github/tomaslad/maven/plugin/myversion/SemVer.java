package io.github.tomaslad.maven.plugin.myversion;

/**
 * https://semver.org
 */
public final class SemVer {

    private final int major;
    private final int minor;
    private final int patch;
    private final int releaseCandidate;
    private final boolean dirty;

    /**
     * Constructs a {@code Version} with the major, minor and patch version numbers.
     *
     * @param major the major version number
     * @param minor the minor version number
     * @param patch the patch version number
     * @throws IllegalArgumentException if one of the version numbers is a negative integer
     */
    SemVer(int major, int minor, int patch, int releaseCandidate, boolean dirty) {
        if (major < 0 || minor < 0 || patch < 0) {
            throw new IllegalArgumentException("Major, minor and patch versions MUST be non-negative integers.");
        }
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.releaseCandidate = releaseCandidate;
        this.dirty = dirty;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

    public int getReleaseCandidate() {
        return releaseCandidate;
    }

    public boolean isDirty() {
        return dirty;
    }


    public static SemVer parse(String version) {
        if (version == null || version.isEmpty()) {
            throw new IllegalArgumentException("Version string is null or empty.");
        }

        int major = -1;
        int minor = -1;
        int patch = -1;
        int releaseCandidate = -1;
        boolean dirty = false;

        // v1.0.0-rc.1-dirty
        String[] parts = version.split("-");
        for (String part : parts) {
            if (part.startsWith("v")) {
                // major.minor.patch
                String[] parts2 = version.split(".");
                major = Integer.parseInt(parts2[0]);
                minor = Integer.parseInt(parts2[1]);
                patch = Integer.parseInt(parts2[2]);
            } else if (part.startsWith("rc")) {
                // release candidate
                String part2 = part.replace("rc.", "");
                releaseCandidate = Integer.parseInt(part2);
            } else if (part.equals("-dirty")) {
                // dirty
                dirty = true;
            }
        }

        return new SemVer(major, minor, patch, releaseCandidate, dirty);

    }
}
