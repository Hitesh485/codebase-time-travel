package com.codebase.time_traveller.testexecution;

import com.codebase.time_traveller.git.GitCheckoutService;

import java.util.ArrayList;
import java.util.List;

public class TestExecutionFacade {

    private final GitCheckoutService checkoutService;
    private final TestExecutionService testService;

    public TestExecutionFacade(GitCheckoutService checkoutService,
                               TestExecutionService testService) {
        this.checkoutService = checkoutService;
        this.testService = testService;
    }

    public List<TestResult> executeForCommits(List<String> commits) throws Exception {
        List<TestResult> results = new ArrayList<>();

        for (String commit : commits) {
            checkoutService.checkout(commit);
            boolean passed = testService.runTests();
            results.add(new TestResult(commit, passed));
        }

        return results;
    }
}
