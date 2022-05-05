package io.github.tomaslad.maven.plugin.myversion;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@UtilityClass
public class GitUtils {

    public static GitDescribe describe() {
        try {
            // https://git-scm.com/docs/git-describe
            Process process = Runtime.getRuntime().exec("git describe --tags --always --first-parent --match=v* --dirty");
            int exitValue = process.waitFor();
            if (exitValue != 0) {
                log.warn("exitValue: {}", exitValue);
            }
            String output = IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);

           return GitDescribe.parse(output);
        } catch (IOException | InterruptedException e) {
            log.error("Read latest tag error: ", e);
            throw new RuntimeException(e);
        }
    }

    public static void tag(String version) {
        try {
            // https://git-scm.com/docs/git-tag
            Process process = Runtime.getRuntime().exec("git tag --annotate " + version);
            int exitValue = process.waitFor();
            if (exitValue != 0) {
                log.warn("exitValue: {}", exitValue);
            }
        } catch (IOException | InterruptedException e) {
            log.error("Set tag error: ", e);
            throw new RuntimeException(e);
        }
    }
}
