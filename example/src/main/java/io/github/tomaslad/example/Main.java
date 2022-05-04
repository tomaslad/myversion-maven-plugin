package io.github.tomaslad.example;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.*;

import java.io.IOException;
import java.util.List;

class Main {

    public static void main(String args[]) throws GitAPIException, IOException {
        Repository repository = new RepositoryBuilder().readEnvironment().findGitDir().build();
        ObjectId objectId = repository.resolve(Constants.HEAD);
        String tagName = getTag(repository);
    }

    private static String getTag(Repository repo) throws GitAPIException {
        try (Git git = Git.wrap(repo)) {
            List<Ref> refs = git.tagList().call();
            for (Ref ref : refs) {
                System.out.println("ref: " + ref.getName());
            }
            return "ahoj";
        }
    }
}