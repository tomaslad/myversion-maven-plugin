package io.github.tomaslad.example;

import org.buildobjects.process.ProcBuilder;
import org.buildobjects.process.ProcResult;

class Main {

    public static void main(String args[]) {
        ProcResult result = new ProcBuilder("git")
                .withArgs("describe", "--tags", "--always", "--first-parent")
                .run();

        System.out.println("Output: " + result.getOutputString());
    }

}