package com.codebase.time_traveller.timetravel.model;

public class TimeTravelResult {
    private final boolean success;
    private final String message;
    private final String currentCommit;

    public TimeTravelResult(boolean success, String message, String currentCommit) {
        this.success = success;
        this.message = message;
        this.currentCommit = currentCommit;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getCurrentCommit() {
        return currentCommit;
    }
}
