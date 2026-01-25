package com.codebase.time_traveller;

import com.codebase.time_traveller.git.GitCheckoutService;
import com.codebase.time_traveller.testexecution.TestExecutionFacade;
import com.codebase.time_traveller.testexecution.TestExecutionService;
import com.codebase.time_traveller.testexecution.TestResult;

import java.util.List;

public class Day12Runner {
    public static void main(String[] args) throws Exception {

        List<String> commits = List.of(
                "1463b42d",
                "f431baf0",
                "579a79a5"
        );

        GitCheckoutService checkoutService =
                new GitCheckoutService("PATH_TO_YOUR_REPO");

        TestExecutionFacade facade =
                new TestExecutionFacade(
                        checkoutService,
                        new TestExecutionService()
                );

        List<TestResult> results = facade.executeForCommits(commits);

        results.forEach(r ->
                System.out.println(
                        "Commit " + r.getCommitId() +
                                " → " + (r.isPassed() ? "PASS" : "FAIL")
                )
        );
    }
}
