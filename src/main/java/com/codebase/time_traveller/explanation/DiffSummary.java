package com.codebase.time_traveller.explanation;

public class DiffSummary {
    private int totalFiles;
    private int linesAdded;
    private int linesRemoved;

    public void incrementFiles() {
        totalFiles++;
    }

    public void addLinesAdded(int count) {
        linesAdded += count;
    }

    public void addLinesRemoved(int count) {
        linesRemoved += count;
    }

    public int getTotalFiles() {
        return totalFiles;
    }

    public int getLinesAdded() {
        return linesAdded;
    }

    public int getLinesRemoved() {
        return linesRemoved;
    }
}
