package com.codebase.time_traveller.explanation;

import com.codebase.time_traveller.diff.model.FileDiff;
import com.codebase.time_traveller.diff.model.LineChange;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChangeClassificationService {

    public List<String> classify(FileDiff fileDiff) {

        List<String> causes = new ArrayList<>();

        for (LineChange change : fileDiff.getChanges()) {
            switch (change.getType()) {
                case LINE_REMOVED ->
                        causes.add("Code removed (possible logic removal)");
                case LINE_ADDED ->
                        causes.add("New logic added");
                case LINE_MODIFIED ->
                        causes.add("Existing logic modified");
            }
        }

        return causes;
    }
}
