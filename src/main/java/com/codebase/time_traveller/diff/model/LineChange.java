package com.codebase.time_traveller.diff.model;

import com.codebase.time_traveller.explanation.ChangeType;

public class LineChange {

    private final ChangeType type;
    private final String content;

    public LineChange(ChangeType type, String content) {
        this.type = type;
        this.content = content;
    }

    public ChangeType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
