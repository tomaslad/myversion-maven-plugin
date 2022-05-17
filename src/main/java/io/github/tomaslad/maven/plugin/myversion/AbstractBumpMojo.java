package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.git.GitDescribe;
import io.github.tomaslad.maven.plugin.myversion.git.GitUtils;
import io.github.tomaslad.maven.plugin.myversion.semver.SemVer;
import io.github.tomaslad.maven.plugin.myversion.semver.SemVerUtils;
import lombok.SneakyThrows;
import org.apache.maven.plugin.AbstractMojo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractBumpMojo extends AbstractMojo {

    @SneakyThrows
    @Override
    public void execute() {
        InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/git.properties");
        Properties prop = new Properties();
        prop.load(input);
        if (prop.getProperty("checkUncommitedChanges").equals("1") && GitUtils.hasUncommitedChanges()) {
            getLog().error("Repository has uncommited changes");
        } else {
            GitDescribe describe = GitUtils.describe();
            SemVer semVer1 = SemVerUtils.parse(describe.getTagName());
            SemVer semVer2 = bump(semVer1);
            getLog().info("Bump version from " + semVer1 + " to " + semVer2);
            GitUtils.tag(semVer2.toString());
        }
    }

    protected abstract SemVer bump(SemVer semVer);
}
