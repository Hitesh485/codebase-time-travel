package com.codebase.time_traveller.diff.model;

import org.eclipse.jgit.diff.DiffEntry;

public class LineChange {

    private final DiffEntry.ChangeType type;
    private final String content;

    public LineChange(DiffEntry.ChangeType type, String content) {
        this.type = type;
        this.content = content;
    }

    public DiffEntry.ChangeType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
