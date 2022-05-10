package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.git.GitDescribe;
import io.github.tomaslad.maven.plugin.myversion.git.GitDescribeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class GitDescribeUtilsTest {

    GitDescribe.GitDescribeBuilder builder = GitDescribe.builder();

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, Integer.MAX_VALUE})
    public void parseCommitDistanceNonZero(int number) {
        assertEquals(builder.tagName("v1.0.0rc.1").commitDistance(number).commitAbbrev("5cd2f15").dirty(true).build(),
                GitDescribeUtils.parse(String.format("v1.0.0-rc.1-%s-g5cd2f15-dirty", number)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, Integer.MAX_VALUE})
    public void parseParameterizedRc(int number) {
        assertEquals(builder.tagName(String.format("v1.0.0rc.%s", number)).dirty(true).build(),
                GitDescribeUtils.parse(String.format("v1.0.0-rc.%s-dirty", number)));

    }

    @ParameterizedTest
    @ValueSource(ints = 0)
    public void parseCommitDistanceZero(int number) {
        assertEquals(builder.tagName("v2.0.0").commitDistance(number).commitAbbrev("3cd23f6").build(),
                GitDescribeUtils.parse("v2.0.0-g3cd23f6"));

    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, Integer.MAX_VALUE})
    public void parseParameterizedVersions(int number) {
        assertEquals(builder.tagName(String.format("v1$s.1$s.1$s", number)).build(),
                GitDescribeUtils.parse(String.format("v1$s.1$s.1$s", number)));

    }
}