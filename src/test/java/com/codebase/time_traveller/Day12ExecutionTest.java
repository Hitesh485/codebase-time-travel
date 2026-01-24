package com.codebase.time_traveller;

import com.codebase.time_traveller.git.GitCheckoutService;
import com.codebase.time_traveller.testexecution.TestExecutionFacade;
import com.codebase.time_traveller.testexecution.TestExecutionService;
import com.codebase.time_traveller.testexecution.TestResult;
import org.junit.jupiter.api.Test;

import java.util.List;


public class Day12ExecutionTest {

    @Test
    void runTestsForEachCommit() throws Exception {

        // 1️. List commits in chronological order (old → new)
        List<String> commits = List.of(
                "1463b42",
                "f431baf",
                "579a79a",
                "1038b3c"
        );

        // 2️. Initialize services
        GitCheckoutService checkoutService =
                new GitCheckoutService(
                        System.getProperty("user.dir") // project root
//                        System.getProperty("D:\\java_spirng_boot_project\\time_traveller\\time_traveller") // project root
                );

        TestExecutionFacade facade =
                new TestExecutionFacade(
                        checkoutService,
                        new TestExecutionService()
                );

        // 3️. Execute tests per commit
        List<TestResult> results = facade.executeForCommits(commits);

        // 4️. Print results (temporary console output)
        System.out.println("\n===== DAY 12 TEST RESULTS =====");
        results.forEach(result ->
                System.out.println(
                        "Commit " + result.getCommitId() +
                                " → " + (result.isPassed() ? "PASS" : "FAIL")
                )
        );
        System.out.println("================================");
    }
}
