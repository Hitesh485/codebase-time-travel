package com.codebase.time_traveller.api;

import com.codebase.time_traveller.api.dto.RegressionDTO;
import com.codebase.time_traveller.api.dto.TimeTravelResponse;
import com.codebase.time_traveller.diff.model.FileDiff;
import com.codebase.time_traveller.git.GitCommitService;
import com.codebase.time_traveller.regression.RegressionDetectionService;
import com.codebase.time_traveller.regression.RegressionResult;
import com.codebase.time_traveller.testexecution.TestExecutionFacade;
import com.codebase.time_traveller.testexecution.TestResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeTravelFacade {

    private final TestExecutionFacade testExecutionFacade;
    private final RegressionDetectionService regressionService;
    private final GitCommitService commitService;
    private final BinaryRegressionService binaryRegressionService;

    public TimeTravelFacade(TestExecutionFacade testExecutionFacade,
                            RegressionDetectionService regressionService, GitCommitService commitService,
                            BinaryRegressionService binaryRegressionService) {
        this.testExecutionFacade = testExecutionFacade;
        this.regressionService = regressionService;
        this.commitService = commitService;
    }

    public TimeTravelResponse analyze() throws Exception {

        List<String> commits = commitService.getAllCommits();

        List<TestResult> results =
                testExecutionFacade.executeForCommits(commits);

        RegressionResult regression =
                binaryRegressionService.findRegression(commits);

//        RegressionResult regression =
//                regressionService.detectRegression(results);

        if (regression == null) {
            return new TimeTravelResponse(false, null);
        }

        List<String> changedFiles =
                regression.getDiffs()
                        .stream()
                        .map(FileDiff::getFilePath)
                        .toList();

        RegressionDTO dto = new RegressionDTO(
                regression.getLastPassingCommit(),
                regression.getFirstFailingCommit(),
                regression.getDiffs()
                        .stream()
                        .map(d -> d.getFilePath())
                        .toList()
        );

        return new TimeTravelResponse(true, dto);
    }
}
