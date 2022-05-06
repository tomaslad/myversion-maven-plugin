package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.semver.SemVer;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "bump-major", defaultPhase = LifecyclePhase.NONE)
public class BumpMajorMojo extends AbstractBumpMojo {

    @Override
    protected SemVer bump(SemVer semVer) {
        getLog().debug("Bump MAJOR version");
        return semVer.withBumpMajor();
    }

}