package io.github.tomaslad.maven.plugin.myversion.git;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;

@Slf4j
@UtilityClass
public final class GitUtils {

    public static GitDescribe describe() {
        try {
            // https://git-scm.com/docs/git-describe
            Process process = Runtime.getRuntime().exec("git describe --tags --always --first-parent --match=v* --dirty");
            int exitValue = process.waitFor();
            if (exitValue != 0) {
                log.warn("exitValue: {}", exitValue);
            }
            String output = IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);
            return GitDescribeUtils.parse(output);
        } catch (IOException | InterruptedException e) {
            log.error("Read latest tag error: ", e);
            throw new RuntimeException(e);
        }
    }

    public static void tag(String version) {
        try {
            // https://git-scm.com/docs/git-tag
            Process process = Runtime.getRuntime().exec("git tag --annotate " + version + " --message \" Release " + version + "\"");
            int exitValue = process.waitFor();
            if (exitValue != 0) {
                log.warn("exitValue: {}", exitValue);
            }
        } catch (IOException | InterruptedException e) {
            log.error("Set tag error: ", e);
            throw new RuntimeException(e);
        }
    }

    public static boolean hasUncommitedChanges() throws IOException, GitAPIException {
        Repository repo = new FileRepository(System.getProperty("user.dir") + "/.git" );
        Git git = new Git( repo );
        Status status = git.status().call();
        Set<String> modified = status.getModified();
        return ( modified.size() != 0 );
    }
}
