package io.github.tomaslad.maven.plugin.myversion.semver;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public final class SemVerUtils {

    public static SemVer parse(String version) {
        if (version == null || version.isEmpty()) {
            throw new IllegalArgumentException("Version string is null or empty.");
        }

        SemVer.SemVerBuilder builder = SemVer.builder();

        // v1.0.0
        // v1.0.0-rc.1
        String[] parts = version.split("-");

        for (String part : parts) {
            if (part.startsWith("v")) {
                String[] parts2 = part.split("\\.");
                builder.major(Integer.parseInt(parts2[0].substring(1)));
                builder.minor(Integer.parseInt(parts2[1]));
                builder.patch(Integer.parseInt(parts2[2]));
            } else if (part.startsWith("rc")) {
                String part2 = part.replace("rc.", "");
                builder.releaseCandidate(Integer.parseInt(part2));
            }
        }

        return builder.build();

    }
}
