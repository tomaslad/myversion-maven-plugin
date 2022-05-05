package io.github.tomaslad.maven.plugin.myversion;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class GitUtilsTest {

    @Test
    public void test() {
        GitUtils.writeTag2("vAhoj");
    }
}
