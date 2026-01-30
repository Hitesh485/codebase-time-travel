package com.codebase.time_traveller.regression;

import com.codebase.time_traveller.diff.DiffService;
import com.codebase.time_traveller.diff.model.FileDiff;
import com.codebase.time_traveller.testexecution.TestExecutionFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinaryRegressionService {
    private final TestExecutionFacade testExecutionFacade;
    private final DiffService diffService;

    public BinaryRegressionService(TestExecutionFacade testExecutionFacade,
                                   DiffService diffService) {
        this.testExecutionFacade = testExecutionFacade;
        this.diffService = diffService;
    }

    public RegressionResult findRegression(List<String> commits) throws Exception {

        int left = 0;
        int right = commits.size() - 1;

        String lastPass = null;
        String firstFail = null;

        while (left <= right) {
            int mid = (left + right) / 2;
            String commit = commits.get(mid);

            boolean passed =
                    testExecutionFacade
                            .executeForCommits(List.of(commit))
                            .get(0)
                            .isPassed();

            if (passed) {
                lastPass = commit;
                left = mid + 1;
            } else {
                firstFail = commit;
                right = mid - 1;
            }
        }

        if (lastPass == null || firstFail == null) {
            return null; // no regression
        }

        List<FileDiff> diffs =
                diffService.diffBetweenCommits(lastPass, firstFail);

        return new RegressionResult(lastPass, firstFail, diffs);
    }

}
