package com.codebase.time_traveller.git;

import org.eclipse.jgit.api.Git;

import java.io.File;

public class GitCheckoutService {

    private final Git git;

    public GitCheckoutService(String repoPath) throws Exception {
        this.git = Git.open(new File(repoPath));
    }

    public void checkout(String commitId) throws Exception {
        git.checkout()
                .setName(commitId)
                .call();
    }
}
