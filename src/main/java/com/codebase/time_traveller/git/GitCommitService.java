package com.codebase.time_traveller.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class GitCommitService {

    private final Git git;

    public GitCommitService() throws Exception {
        this.git = Git.open(new java.io.File(System.getProperty("user.dir")));
    }

    /**
     * Returns commits in chronological order (old → new)
     */
    public List<String> getAllCommits() throws Exception {

        Iterable<RevCommit> log = git.log().call();
        List<String> commits = new ArrayList<>();

        for (RevCommit commit : log) {
            commits.add(commit.getName());
        }

        // JGit gives newest → oldest, so reverse
        Collections.reverse(commits);
        return commits;
    }
}
