package com.codebase.time_traveller.regression;

import com.codebase.time_traveller.diff.model.FileDiff;

public class RegressionExplanationService   {
    public void printExplanation(RegressionResult result) {

        System.out.println("\n===== REGRESSION DETECTED =====");
        System.out.println("Last passing commit : " + result.getLastPassingCommit());
        System.out.println("First failing commit: " + result.getFirstFailingCommit());
        System.out.println("\nFiles changed:");

        for (FileDiff diff : result.getDiffs()) {
            System.out.println("- " + diff.getFilePath() +
                    " (changes: " + diff.getChanges().size() + ")");
        }

        System.out.println("================================");
    }
}
