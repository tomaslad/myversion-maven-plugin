package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.git.GitDescribe;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class GitDescribeTest {

    GitDescribe.GitDescribeBuilder builder = GitDescribe.builder();

    @Test
    public void isReleaseTest() {
        GitDescribe gitDescribe = builder.commitDistance(0).build();
        assertTrue(gitDescribe.isReleaseCommit());
    }

    @Test
    public void notReleaseTest() {
        GitDescribe gitDescribe = builder.commitDistance(3).build();
        assertFalse(gitDescribe.isReleaseCommit());
    }

}