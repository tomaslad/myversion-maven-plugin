package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.git.GitDescribe;
import io.github.tomaslad.maven.plugin.myversion.git.GitDescribeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class GitDescribeUtilsTest {

    GitDescribe.GitDescribeBuilder builder = GitDescribe.builder();

    @Test
    public void parseVersion1() {
        assertEquals(builder.tagName("v1.0.0rc.1").commitDistance(5).commitAbbrev("5cd2f15").dirty(true).build(),
                GitDescribeUtils.parse("v1.0.0-rc.1-5-g5cd2f15-dirty"));
    }

    @Test
    public void parseVersion2() {
        assertEquals(builder.tagName("v1.0.0rc.1").dirty(true).build(),
                GitDescribeUtils.parse("v1.0.0-rc.1-dirty"));

    }

    @Test
    public void parseVersion3() {
        assertEquals(builder.tagName("v2.0.0").commitDistance(2).commitAbbrev("3cd23f6").build(),
                GitDescribeUtils.parse("v2.0.0-2-g3cd23f6"));

    }

    @Test
    public void parseVersion4() {
        assertEquals(builder.tagName("v3.1.3").build(),
                GitDescribeUtils.parse("v3.1.3"));

    }
}