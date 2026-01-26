package com.codebase.time_traveller.api.dto;

public class TimeTravelResponse {
    private final boolean regressionFound;
    private final RegressionDTO regression;

    public TimeTravelResponse(boolean regressionFound, RegressionDTO regression) {
        this.regressionFound = regressionFound;
        this.regression = regression;
    }

    public boolean isRegressionFound() {
        return regressionFound;
    }

    public RegressionDTO getRegression() {
        return regression;
    }
}
