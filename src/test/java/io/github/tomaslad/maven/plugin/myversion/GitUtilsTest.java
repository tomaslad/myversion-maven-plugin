package io.github.tomaslad.maven.plugin.myversion;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

@Slf4j
public class GitUtilsTest {

    @Test
    public void test() {
        String s = GitUtils.readLatestTag();
        System.out.println("S: " + s);

        GitUtils.writeTag2("vAhoj");
    }
}
