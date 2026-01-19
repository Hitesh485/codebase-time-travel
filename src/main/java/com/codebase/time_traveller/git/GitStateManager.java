package com.codebase.time_traveller.git;
//    Save Git state before time travel and restore it later.

import com.codebase.time_traveller.git.model.GitState;
import java.io.IOException;

public class GitStateManager {
/*
    1. Capture current state:
        Ask GitRepositoryService: “Where are we right now?”
        Store that information safely in memory

    2.   Store it safely in memory

    3.    Restore Git to original state

    4.    Protect against misuse (invalid calls)

*/
private final GitRepositoryService gitRepositoryService;

    // Stored in memory (single active state)
    private GitState savedState;

    public GitStateManager(GitRepositoryService gitRepositoryService) {
        this.gitRepositoryService = gitRepositoryService;
    }

    // 1️⃣ Capture current state
    public void saveCurrentState() throws IOException {
        if (savedState != null) {
            throw new IllegalStateException("Git state already saved");
        }

        String currentBranch = gitRepositoryService.getCurrentBranch();
        String currentCommit = gitRepositoryService.getCurrentCommitHash();

        this.savedState = new GitState(currentBranch, currentCommit);
    }

    // 2️⃣ Expose saved state (read-only)
    public GitState getSavedState() {
        if (savedState == null) {
            throw new IllegalStateException("No Git state saved");
        }
        return savedState;
    }

    // 3️⃣ Restore original state
    public void restoreState() throws IOException {
        if (savedState == null) {
            throw new IllegalStateException("No Git state to restore");
        }

        // Prefer restoring by branch (safer)
        if (savedState.getBranchName() != null) {
            gitRepositoryService.checkoutBranch(savedState.getBranchName());
        } else {
            gitRepositoryService.checkoutCommit(savedState.getCommitHash());
        }

        // Clear state after successful restore
        clearState();
    }

    // 4️⃣ Protection & cleanup
    public void clearState() {
        this.savedState = null;
    }

}











