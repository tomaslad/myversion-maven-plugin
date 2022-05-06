package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.semver.SemVer;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "bump-minor", defaultPhase = LifecyclePhase.NONE)
public class BumpMinorMojo extends AbstractBumpMojo {

    @Override
    protected SemVer bump(SemVer semVer) {
        getLog().debug("Bump MINOR version");
        return semVer.withBumpMinor();
    }

}