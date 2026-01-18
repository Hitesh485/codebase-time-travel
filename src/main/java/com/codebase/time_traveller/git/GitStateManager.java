package com.codebase.time_traveller.git;
//    Save Git state before time travel and restore it later.

import com.codebase.time_traveller.git.model.GitState;
import java.io.IOException;

public class GitStateManager {
/*
    1. Capture current state:
        Ask GitRepositoryService: “Where are we right now?”
        Store that information safely in memory
*/
    private final GitRepositoryService gitRepositoryService;
    private GitState savedState;

    public GitStateManager(GitRepositoryService gitRepositoryService) {
        this.gitRepositoryService = gitRepositoryService;
    }

    // Step 1: Capture current state
    public void saveCurrentState() throws IOException {
        if (savedState != null) {
            throw new IllegalStateException("Git state already saved");
        }

        String branch = gitRepositoryService.getCurrentBranch();
        String commit = gitRepositoryService.getCurrentCommitHash();

        this.savedState = new GitState(branch, commit);
    }

    // Step 2: Access saved state
    public GitState getSavedState() {
        if (savedState == null) {
            throw new IllegalStateException("No Git state saved");
        }
        return savedState;
    }

    // Step 3: Clear state (after restore)
    public void clearState() {
        this.savedState = null;
    }

}











