package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.semver.SemVer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class SemVerTest {

    SemVer.SemVerBuilder builder = SemVer.builder();
    SemVer semVer = builder.major(1).minor(0).patch(0).releaseCandidate(2).build();

    @Test
    public void semVerTest(){
        assertEquals("v1.0.0-rc.2", semVer.toString());
    }

    @Test
    public void semVerBumpMajorTest(){
        assertEquals("v2.0.0", semVer.withBumpMajor().toString());
    }

    @Test
    public void semVerBumpMinorTest(){
        assertEquals("v1.1.0", semVer.withBumpMinor().toString());
    }

    @Test
    public void semVerBumpPatchTest(){
        assertEquals("v1.0.1", semVer.withBumpPatch().toString());
    }

    @Test
    public void semVerBumpReleaseCandidateTest(){
        assertEquals("v1.0.0-rc.3", semVer.withBumpReleaseCandidate().toString());
    }

}