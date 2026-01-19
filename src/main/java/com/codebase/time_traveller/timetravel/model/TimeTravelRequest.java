package com.codebase.time_traveller.timetravel.model;

public class TimeTravelRequest {
    private String targetCommitHash;

    public TimeTravelRequest(String targetCommitHash) {
        this.targetCommitHash = targetCommitHash;
    }

    public String getTargetCommitHash() {
        return targetCommitHash;
    }
}
