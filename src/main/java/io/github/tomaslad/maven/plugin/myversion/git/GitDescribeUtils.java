package io.github.tomaslad.maven.plugin.myversion.git;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@UtilityClass
public final class GitDescribeUtils {

    private static final String PREFIX_VERSION = "v";
    private static final String PREFIX_RELEASE_CANDIDATE = "rc.";
    private static final String PREFIX_COMMIT_ABBREV = "g";
    private static final String REGEX_COMMIT_DISTANCE = "^[1-9]*$";

    public static GitDescribe parse(String output) {
        if (StringUtils.isEmpty(output)) {
            throw new IllegalArgumentException("describe string is null or empty.");
        }

        StringBuilder version = new StringBuilder();
        GitDescribe.GitDescribeBuilder builder = GitDescribe.builder();

        // v1.0.0-rc.1-dirty soucastny commit je otagovan
        // v1.0.0-rc.1-5-g5cd2f15-dirty tag je na nejakem starsim commitu
        String[] parts = output.split("-");

        for (String part : parts) {
            if (part.startsWith(PREFIX_VERSION)) {
                version.append(part);
            } else if (part.startsWith(PREFIX_RELEASE_CANDIDATE)) {
                version.append(part);
            } else if (part.matches(REGEX_COMMIT_DISTANCE)) {
                builder.commitDistance(Integer.parseInt(part));
            } else if (part.startsWith(PREFIX_COMMIT_ABBREV)) {
                builder.commitAbbrev(part.substring(1));
            } else if (part.equals("dirty")) {
                builder.dirty(true);
            }
        }

        return builder.tagName(version.toString()).build();
    }
}
