package com.codebase.time_traveller.explanation;

import java.util.List;
import java.util.Map;

public class ExplanationResult {

    private final List<String> rootCauseSummary;
    private final Map<String, List<String>> fileExplanations;

    public ExplanationResult(List<String> rootCauseSummary,
                             Map<String, List<String>> fileExplanations) {
        this.rootCauseSummary = rootCauseSummary;
        this.fileExplanations = fileExplanations;
    }

    public List<String> getRootCauseSummary() {
        return rootCauseSummary;
    }

    public Map<String, List<String>> getFileExplanations() {
        return fileExplanations;
    }
}
