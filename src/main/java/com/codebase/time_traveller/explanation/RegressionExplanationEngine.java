package com.codebase.time_traveller.explanation;

import com.codebase.time_traveller.diff.model.FileDiff;
import com.codebase.time_traveller.regression.RegressionResult;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegressionExplanationEngine {

    private final ChangeClassificationService classifier;

    public RegressionExplanationEngine(ChangeClassificationService classifier) {
        this.classifier = classifier;
    }

    public ExplanationResult explain(RegressionResult regression) {

        Map<String, List<String>> fileExplanations = new HashMap<>();
        Set<String> summary = new HashSet<>();

        for (FileDiff diff : regression.getDiffs()) {
            List<String> causes = classifier.classify(diff);
            fileExplanations.put(diff.getFilePath(), causes);
            summary.addAll(causes);
        }

        return new ExplanationResult(
                new ArrayList<>(summary),
                fileExplanations
        );
    }
}
