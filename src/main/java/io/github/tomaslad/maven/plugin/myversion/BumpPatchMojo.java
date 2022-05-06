package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.semver.SemVer;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "bump-minor", defaultPhase = LifecyclePhase.NONE)
public class BumpPatchMojo extends AbstractBumpMojo {

    @Override
    protected SemVer bump(SemVer semVer) {
        getLog().debug("Bump PATCH version");
        return semVer.withBumpPatch();
    }

}