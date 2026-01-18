package com.codebase.time_traveller.git;

import java.io.IOException;

public class TimeTravelService {

    private final GitRepositoryService gitRepositoryService;
    private final GitStateManager gitStateManager;

    public TimeTravelService(GitRepositoryService gitRepositoryService,
                             GitStateManager gitStateManager) {
        this.gitRepositoryService = gitRepositoryService;
        this.gitStateManager = gitStateManager;
    }

    // Step 1: Time travel to a specific commit
    public void travelToCommit(String commitHash) throws IOException {
        // Save current position (only once)
        gitStateManager.saveCurrentState();

        // Move repository to target commit
        gitRepositoryService.checkoutCommit(commitHash);
    }

    // Step 2: Return back to original position
    public void returnToOriginalState() throws IOException {
        GitState state = gitStateManager.getSavedState();

        // Prefer restoring by branch (safer)
        if (state.getBranchName() != null) {
            gitRepositoryService.checkoutBranch(state.getBranchName());
        } else {
            gitRepositoryService.checkoutCommit(state.getCommitHash());
        }

        // Clear saved state after successful restore
        gitStateManager.clearState();
    }
}
