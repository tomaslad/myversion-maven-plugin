package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.semver.SemVer;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "bump-release-candidate", defaultPhase = LifecyclePhase.NONE)
public class BumpReleaseCandidate extends AbstractBumpMojo {

    @Override
    protected SemVer bump(SemVer semVer) {
        getLog().debug("Bump Release Candidate version");
        return semVer.withBumpReleaseCandidate();
    }

}