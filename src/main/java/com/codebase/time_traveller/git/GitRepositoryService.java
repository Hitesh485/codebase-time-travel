package com.codebase.time_traveller.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class GitRepositoryService {

    private final Repository repository;
// method to open a repo
    public GitRepositoryService(String repoPath) throws IOException {
        this.repository = Git.open(new File(repoPath)).getRepository();
    }

    // method to check Where are we right now (branch)
    public String getCurrentBranch() throws IOException {
        return repository.getBranch();
    }

    // method to find Where are we right now? (commit hash)
    public String getCurrentCommitHash() throws IOException {
        ObjectId head = repository.resolve("HEAD");
        return head != null ? head.getName() : null;
    }
     //return repo to statemanager
    public Repository getRepository() {
        return repository;
    }



    public void checkoutCommit(String commitHash) {
        try {
            Git git = new Git(repository);
            git.checkout()
                    .setName(commitHash)
                    .call();
        } catch (GitAPIException e) {
            throw new RuntimeException("Failed to checkout commit: " + commitHash, e);
        }
    }

    public void checkoutBranch(String branchName) {
        try {
            Git git = new Git(repository);
            git.checkout()
                    .setName(branchName)
                    .call();
        } catch (GitAPIException e) {
            throw new RuntimeException("Failed to checkout branch: " + branchName, e);
        }
    }



}
