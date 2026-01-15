package com.codebase.time_traveller.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class GitHistoryService {
    public void printLastCommits(int limit) {
        try {
            Repository repository = new FileRepositoryBuilder()
                    .setGitDir(new File(".git"))
                    .readEnvironment()
                    .findGitDir()
                    .build();

            Git git = new Git(repository);

            Iterable<RevCommit> commits = git.log().setMaxCount(limit).call();

            for (RevCommit commit : commits) {
                System.out.println("Here are the git commits");
                System.out.println("Commit ID: " + commit.getName());
                System.out.println("Message : " + commit.getShortMessage());
                System.out.println("Time    : " + commit.getAuthorIdent().getWhen());
                System.out.println("-----------------------------------");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read git history", e);
        }
    }
}
