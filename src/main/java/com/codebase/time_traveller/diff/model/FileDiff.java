package com.codebase.time_traveller.diff.model;

import java.util.ArrayList;

public class FileDiff {
    private final String filePath;
    private final List<LineChange> changes = new ArrayList<>();

    public FileDiff(String filePath) {
        this.filePath = filePath;
    }

    public void addLineChange(LineChange change) {
        changes.add(change);
    }

    public String getFilePath() {
        return filePath;
    }

    public List<LineChange> getChanges() {
        return changes;
    }
}
