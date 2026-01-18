package com.codebase.time_traveller.git.model;
/*
    1. Current branch name (preferred)

    2. OR current commit hash (fallback)

    3. Maybe a flag like “detached HEAD” (later)
*/

public class GitState {
    private final String branchName;
    private final String commitHash;

    public GitState(String branchName, String commitHash) {
        this.branchName = branchName;
        this.commitHash = commitHash;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getCommitHash() {
        return commitHash;
    }

}
