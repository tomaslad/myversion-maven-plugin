package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.git.GitUtils;
import io.github.tomaslad.maven.plugin.myversion.git.GitDescribe;
import io.github.tomaslad.maven.plugin.myversion.semver.SemVer;
import io.github.tomaslad.maven.plugin.myversion.semver.SemVerUtils;
import org.apache.maven.AbstractMavenLifecycleParticipant;
import org.apache.maven.MavenExecutionException;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.logging.Logger;

@Component(role = AbstractMavenLifecycleParticipant.class, hint = "myversion")
public class MyVersionMavenLifecycleParticipant extends AbstractMavenLifecycleParticipant {

    @Requirement
    private Logger logger;

    @Override
    public void afterProjectsRead(MavenSession mavenSession) throws MavenExecutionException {
        logger.info("Using myversion-maven-plugin");

        GitDescribe describe = GitUtils.describe();
        SemVer semVer = SemVerUtils.parse(describe.getTagName());
        String version = semVer.toString();

        if (!describe.isReleaseCommit()) {
            version += "-SNAPSHOT";
        }

        for (MavenProject mavenProject : mavenSession.getAllProjects()) {
            mavenProject.setVersion(version);
        }
    }

}