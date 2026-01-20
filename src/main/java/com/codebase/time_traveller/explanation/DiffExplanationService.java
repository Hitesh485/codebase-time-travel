package com.codebase.time_traveller.explanation;

import com.codebase.time_traveller.diff.model.FileDiff;
import com.codebase.time_traveller.diff.model.LineChange;

import java.util.List;

    public class DiffExplanationService {
        public DiffSummary summarize(List<FileDiff> diffs) {
            DiffSummary summary = new DiffSummary();

            for (FileDiff file : diffs) {
                summary.incrementFiles();

                for (LineChange change : file.getChanges()) {
                    if (change.getType() == ChangeType.LINE_ADDED) {
                        summary.addLinesAdded(1);
                    }
                    if (change.getType() == ChangeType.LINE_REMOVED) {
                        summary.addLinesRemoved(1);
                    }
                }
            }
            return summary;
        }
}
