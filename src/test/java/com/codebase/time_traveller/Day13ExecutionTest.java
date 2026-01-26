package com.codebase.time_traveller;

import com.codebase.time_traveller.diff.DiffService;
import com.codebase.time_traveller.regression.*;
import com.codebase.time_traveller.testexecution.TestResult;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class Day13ExecutionTest {

    @Test
    void detectRegression() throws Exception {

        List<TestResult> results = List.of(
                new TestResult("1463b42d", true),
                new TestResult("f431baf0", true),
                new TestResult("579a79a5", false)
        );

        Repository repo = new FileRepositoryBuilder()
                .setGitDir(new File(".git"))
                .build();

        DiffService diffService = new DiffService(repo);
        RegressionDetectionService detector =
                new RegressionDetectionService(diffService);

        RegressionResult result = detector.detectRegression(results);

        new RegressionExplanationService().printExplanation(result);
    }
}
