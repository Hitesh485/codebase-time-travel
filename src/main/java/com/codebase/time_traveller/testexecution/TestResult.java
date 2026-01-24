package com.codebase.time_traveller.testexecution;

public class TestResult {
    private final String commitId;
    private final Boolean passed;

    public TestResult(String commitId, boolean passed) {
        this.commitId = commitId;
        this.passed = passed;
    }

    public String getCommitId() {
        return commitId;
    }

    public boolean isPassed() {
        return passed;
    }
}
