package io.github.tomaslad.maven.plugin.myversion;

/**
 * https://semver.org
 */
public final class SemVer {

    private int major;
    private int minor;
    private int patch;
    private int releaseCandidate;

    /**
     * Constructs a {@code Version} with the major, minor and patch version numbers.
     *
     * @param major the major version number
     * @param minor the minor version number
     * @param patch the patch version number
     * @throws IllegalArgumentException if one of the version numbers is a negative integer
     */
    SemVer(int major, int minor, int patch, int releaseCandidate) {
        if (major < 0 || minor < 0 || patch < 0) {
            throw new IllegalArgumentException("Major, minor and patch versions MUST be non-negative integers.");
        }
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.releaseCandidate = releaseCandidate;
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

    public void increaseMajor() {
        major += 1;
        minor = 0;
        patch = 0;
        releaseCandidate = 0;
    }

    public void increaseMinor() {
        minor += 1;
        patch = 0;
        releaseCandidate = 0;
    }

    public void increasePatch() {
        patch += 1;
        releaseCandidate = 0;
    }

    public void increaseReleaseCandidate() {
        releaseCandidate += 1;
    }

    @Override
    public String toString() {
        String version = "v" + major + "." + minor + "." + patch + "";

        if (releaseCandidate > 0) {
            version = version + "rc." + releaseCandidate;
        }

        return version;
    }

    public static SemVer parse(String version) {
        if (version == null || version.isEmpty()) {
            throw new IllegalArgumentException("Version string is null or empty.");
        }

        int major = -1;
        int minor = -1;
        int patch = -1;
        int releaseCandidate = -1;

        // v1.0.0
        // v1.0.0-rc.1
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
            }
        }

        return new SemVer(major, minor, patch, releaseCandidate);

    }
}
