package com.codebase.time_traveller.api;

import com.codebase.time_traveller.regression.RegressionDetectionService;
import com.codebase.time_traveller.regression.RegressionResult;
import com.codebase.time_traveller.testexecution.TestExecutionFacade;
import com.codebase.time_traveller.testexecution.TestResult;

import java.util.List;
import java.util.stream.Collectors;

public class TimeTravelFacade {

    private final TestExecutionFacade testExecutionFacade;
    private final RegressionDetectionService regressionService;

    public TimeTravelFacade(TestExecutionFacade testExecutionFacade,
                            RegressionDetectionService regressionService) {
        this.testExecutionFacade = testExecutionFacade;
        this.regressionService = regressionService;
    }

    public TimeTravelResponse analyze(List<String> commits) throws Exception {

        List<TestResult> results =
                testExecutionFacade.executeForCommits(commits);

        RegressionResult regression =
                regressionService.detectRegression(results);

        if (regression == null) {
            return new TimeTravelResponse(false, null);
        }

        List<String> changedFiles =
                regression.getDiffs()
                        .stream()
                        .map(f -> f.getFilePath())
                        .collect(Collectors.toList());

        RegressionDTO dto = new RegressionDTO(
                regression.getLastPassingCommit(),
                regression.getFirstFailingCommit(),
                changedFiles
        );

        return new TimeTravelResponse(true, dto);
    }
}
