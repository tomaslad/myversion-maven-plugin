package io.github.tomaslad.maven.plugin.myversion.git;

import io.github.tomaslad.maven.plugin.myversion.SemVer;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class SemVerTest {

    @Test
    public void testParse() {
        String v1 = "v2.11.3-rc.2-dirty";
        SemVer semVer = SemVer.parse(v1);
        assertThat(semVer.getMajor()).isEqualTo(1);
        assertThat(semVer.getMinor()).isEqualTo(11);
        assertThat(semVer.getPatch()).isEqualTo(3);

        String v2 = "v2.11.3";
        SemVer semVer2 = SemVer.parse(v1);
        assertThat(semVer2.getMajor()).isEqualTo(1);
        assertThat(semVer2.getMinor()).isEqualTo(11);
        assertThat(semVer2.getPatch()).isEqualTo(3);

        String v3 = "v2.11.3-dirty";
        SemVer semVer3 = SemVer.parse(v1);
        assertThat(semVer3.getMajor()).isEqualTo(1);
        assertThat(semVer3.getMinor()).isEqualTo(11);
        assertThat(semVer3.getPatch()).isEqualTo(3);
    }
}
