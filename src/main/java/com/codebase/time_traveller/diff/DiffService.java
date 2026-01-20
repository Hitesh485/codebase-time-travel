package com.codebase.time_traveller.diff;
import com.codebase.time_traveller.diff.model.FileDiff;
import com.codebase.time_traveller.diff.model.LineChange;
import com.codebase.time_traveller.explanation.ChangeType;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.*;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DiffService {
    private final Repository repository;

    public DiffService(Repository repository) {
        this.repository = repository;
    }

    public List<FileDiff> diffBetweenCommits(String oldCommit, String newCommit) throws Exception {
        List<FileDiff> result = new ArrayList<>();

        try (Git git = new Git(repository)) {

            ObjectId oldTree = repository.resolve(oldCommit + "^{tree}");
            ObjectId newTree = repository.resolve(newCommit + "^{tree}");

            List<DiffEntry> diffs = git.diff()
                    .setOldTree(Utils.prepareTreeParser(repository, oldTree))
                    .setNewTree(Utils.prepareTreeParser(repository, newTree))
                    .call();

            for (DiffEntry entry : diffs) {
                FileDiff fileDiff = new FileDiff(entry.getNewPath());

                DiffFormatter formatter = new DiffFormatter(new ByteArrayOutputStream());
                formatter.setRepository(repository);
                FileHeader fileHeader = formatter.toFileHeader(entry);

                for (HunkHeader hunk : fileHeader.getHunks()) {
                    for (Edit edit : hunk.toEditList()) {
                        if (edit.getType() == Edit.Type.INSERT) {
                            fileDiff.addLineChange(new LineChange(
                                    ChangeType.LINE_ADDED, edit.toString()
                            ));
                        }
                        if (edit.getType() == Edit.Type.DELETE) {
                            fileDiff.addLineChange(new LineChange(
                                    ChangeType.LINE_REMOVED, edit.toString()
                            ));
                        }
                    }
                }
                result.add(fileDiff);
            }
        }
        return result;
    }
}
