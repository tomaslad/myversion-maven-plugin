package io.github.tomaslad.maven.plugin.myversion;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "bump-major", defaultPhase = LifecyclePhase.NONE)
public class BumpMajorMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    public void execute() {
        GitDescribe describe = GitUtils.describe();

        SemVer semVer = SemVer.parse(describe.getVersion());
        semVer.increaseMajor();

        GitUtils.tag(semVer.toString());
    }

}