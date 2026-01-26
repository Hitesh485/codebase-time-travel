package com.codebase.time_traveller.regression;

import com.codebase.time_traveller.diff.DiffService;
import com.codebase.time_traveller.diff.model.FileDiff;
import com.codebase.time_traveller.testexecution.TestResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegressionDetectionService {
    private final DiffService diffService;

    public RegressionDetectionService(DiffService diffService) {
        this.diffService = diffService;
    }

    public RegressionResult detectRegression(List<TestResult> results) throws Exception {

        TestResult lastPass = null;
        TestResult firstFail = null;

        for (TestResult result : results) {
            if (!result.isPassed() && lastPass != null) {
                firstFail = result;
                break;
            }
            lastPass = result;
        }

        if (lastPass == null || firstFail == null) {
            return null; // no regression
        }

        List<FileDiff> diffs =
                diffService.diffBetweenCommits(
                        lastPass.getCommitId(),
                        firstFail.getCommitId()
                );

        return new RegressionResult(
                lastPass.getCommitId(),
                firstFail.getCommitId(),
                diffs
        );
    }
}
