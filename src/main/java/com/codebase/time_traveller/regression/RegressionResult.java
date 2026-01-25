package com.codebase.time_traveller.regression;

import com.codebase.time_traveller.diff.model.FileDiff;
import java.util.List;

public class RegressionResult {
    private final String lastPassingCommit;
    private final String firstFailingCommit;
    private final List<FileDiff> diffs;

    public RegressionResult(String lastPassingCommit,
                            String firstFailingCommit,
                            List<FileDiff> diffs) {
        this.lastPassingCommit = lastPassingCommit;
        this.firstFailingCommit = firstFailingCommit;
        this.diffs = diffs;
    }

    public String getLastPassingCommit() {
        return lastPassingCommit;
    }

    public String getFirstFailingCommit() {
        return firstFailingCommit;
    }

    public List<FileDiff> getDiffs() {
        return diffs;
    }
}
