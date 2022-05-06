package io.github.tomaslad.maven.plugin.myversion.semver;

import lombok.Builder;
import lombok.Value;

/**
 * https://semver.org
 */
@Value
@Builder
public class SemVer {

    int major;
    int minor;
    int patch;
    int releaseCandidate;

    public SemVer withBumpMajor() {
        return SemVer.builder()
                .major(major + 1)
                .minor(0)
                .patch(0)
                .releaseCandidate(0)
                .build();
    }

    public SemVer withBumpMinor() {
        return SemVer.builder()
                .major(major)
                .minor(minor + 1)
                .patch(0)
                .releaseCandidate(0)
                .build();
    }

    public SemVer withBumpPatch() {
        return SemVer.builder()
                .major(major)
                .minor(minor)
                .patch(patch + 1)
                .releaseCandidate(0)
                .build();
    }

    public SemVer withBumpReleaseCandidate() {
        return SemVer.builder()
                .major(major)
                .minor(minor)
                .patch(patch)
                .releaseCandidate(releaseCandidate + 1)
                .build();
    }

    @Override
    public String toString() {
        String version = "v" + major + "." + minor + "." + patch + "";

        if (releaseCandidate > 0) {
            version = version + "rc." + releaseCandidate;
        }

        return version;
    }
}
