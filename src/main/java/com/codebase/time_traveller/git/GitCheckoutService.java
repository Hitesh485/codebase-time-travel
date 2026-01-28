package com.codebase.time_traveller.git;

import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;

import java.io.File;
@Service
public class GitCheckoutService {

    private final Git git;

    public GitCheckoutService(String repoPath) throws Exception {
        this.git = Git.open(new File(System.getProperty("user.dir")));
    }
    public void checkout(String commitId) throws Exception {
        git.checkout()
                .setName(commitId)
                .call();
    }
}
