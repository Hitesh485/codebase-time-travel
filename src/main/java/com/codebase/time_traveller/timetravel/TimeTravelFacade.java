package com.codebase.time_traveller.timetravel;


import com.codebase.time_traveller.git.GitRepositoryService;
import com.codebase.time_traveller.git.GitStateManager;
import com.codebase.time_traveller.git.TimeTravelService;
import com.codebase.time_traveller.timetravel.model.TimeTravelRequest;
import com.codebase.time_traveller.timetravel.model.TimeTravelResult;

public class TimeTravelFacade {
    private final TimeTravelService timeTravelService;
    private final GitRepositoryService gitRepositoryService;
    private final GitStateManager gitStateManager;

    public TimeTravelFacade(TimeTravelService timeTravelService,
                            GitRepositoryService gitRepositoryService,
                            GitStateManager gitStateManager) {
        this.timeTravelService = timeTravelService;
        this.gitRepositoryService = gitRepositoryService;
        this.gitStateManager = gitStateManager;
    }

    // Go to past
    public TimeTravelResult travel(TimeTravelRequest request) {
        try {
            timeTravelService.travelToCommit(request.getTargetCommitHash());

            String currentCommit = gitRepositoryService.getCurrentCommitHash();

            return new TimeTravelResult(
                    true,
                    "Successfully traveled to commit",
                    currentCommit
            );

        } catch (Exception e) {
            return new TimeTravelResult(
                    false,
                    "Time travel failed: " + e.getMessage(),
                    null
            );
        }
    }

    // Return to present
    public TimeTravelResult returnBack() {
        try {
            gitStateManager.restoreState();

            String currentCommit = gitRepositoryService.getCurrentCommitHash();

            return new TimeTravelResult(
                    true,
                    "Returned to original state",
                    currentCommit
            );

        } catch (Exception e) {
            return new TimeTravelResult(
                    false,
                    "Failed to restore original state: " + e.getMessage(),
                    null
            );
        }
    }

}
