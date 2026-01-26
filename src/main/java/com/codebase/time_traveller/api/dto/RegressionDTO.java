package com.codebase.time_traveller.api.dto;

import java.util.List;

public class RegressionDTO {
    private final String lastPassingCommit;
    private final String firstFailingCommit;
    private final List<String> changedFiles;

    public RegressionDTO(String lastPassingCommit, String firstFailingCommit, List<String> changedFiles)
    {
        this.lastPassingCommit = lastPassingCommit;
        this.firstFailingCommit = firstFailingCommit;
        this.changedFiles = changedFiles;
    }

    public String getLastPassingCommit() {
        return lastPassingCommit;
    }

    public String getFirstFailingCommit() {
        return firstFailingCommit;
    }

    public List<String> getChangedFiles() {
        return changedFiles;
    }
}
