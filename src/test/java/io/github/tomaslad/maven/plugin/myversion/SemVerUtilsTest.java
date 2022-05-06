package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.semver.SemVer;
import io.github.tomaslad.maven.plugin.myversion.semver.SemVerUtils;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class SemVerUtilsTest {

    @Test
    public void testParse() {
        String v1 = "v2.11.3-rc.2";
        SemVer semVer = SemVerUtils.parse(v1);
        assertThat(semVer.getMajor()).isEqualTo(2);
        assertThat(semVer.getMinor()).isEqualTo(11);
        assertThat(semVer.getPatch()).isEqualTo(3);
        assertThat(semVer.getReleaseCandidate()).isEqualTo(2);

        String v2 = "v2.11.3";
        SemVer semVer2 = SemVerUtils.parse(v2);
        assertThat(semVer2.getMajor()).isEqualTo(2);
        assertThat(semVer2.getMinor()).isEqualTo(11);
        assertThat(semVer2.getPatch()).isEqualTo(3);
        assertThat(semVer2.getReleaseCandidate()).isEqualTo(0);
    }
}
