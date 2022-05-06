package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.git.GitDescribe;
import io.github.tomaslad.maven.plugin.myversion.git.GitUtils;
import io.github.tomaslad.maven.plugin.myversion.semver.SemVer;
import io.github.tomaslad.maven.plugin.myversion.semver.SemVerUtils;
import org.apache.maven.plugin.AbstractMojo;

public abstract class AbstractBumpMojo extends AbstractMojo {

    @Override
    public void execute() {
        GitDescribe describe = GitUtils.describe();
        SemVer semVer1 = SemVerUtils.parse(describe.getTagName());
        SemVer semVer2 = bump(semVer1);
        getLog().info("Bump version from " + semVer1 + " to " + semVer2);
        GitUtils.tag(semVer2.toString());
    }

    protected abstract SemVer bump(SemVer semVer);
}
