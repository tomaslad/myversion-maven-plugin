package io.github.tomaslad.maven.plugin.counter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;

import java.io.IOException;

@Mojo(name = "parse-version", defaultPhase = LifecyclePhase.VALIDATE, threadSafe = true)
public class ReadRevisionMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {
        try (Repository repository = new RepositoryBuilder().readEnvironment().findGitDir().build()) {
            ObjectId objectId = repository.resolve(Constants.HEAD);
            String tagName = getTag(repository);
            project.getProperties().put("revision", tagName);
        } catch (IOException e) {
            getLog().error(e);
        }
    }

    private String getTag(Repository repo) {
        try (Git git = Git.wrap(repo)) {
            String tagName = git.tag().getName();
            getLog().debug("tagName: " + tagName);
            return tagName;
        }
    }

}