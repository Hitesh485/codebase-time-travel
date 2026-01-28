package com.codebase.time_traveller.diff;
import com.codebase.time_traveller.diff.model.FileDiff;
import com.codebase.time_traveller.diff.model.LineChange;
//import com.codebase.time_traveller.explanation.ChangeType;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.*;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.patch.FileHeader;
import org.eclipse.jgit.patch.HunkHeader;
//import com.codebase.time_traveller.explanation.ChangeType;
import org.eclipse.jgit.diff.DiffEntry;
import com.codebase.time_traveller.diff.DiffUtils;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiffService {
    private final Repository repository;

    public DiffService() throws Exception {
        this.repository = new FileRepositoryBuilder().setGitDir(new File(".git")).build();
    }

    public List<FileDiff> diffBetweenCommits(String oldCommit, String newCommit) throws Exception {
        List<FileDiff> result = new ArrayList<>();

        try (Git git = new Git(repository)) {

            ObjectId oldTree = repository.resolve(oldCommit + "^{tree}");
            ObjectId newTree = repository.resolve(newCommit + "^{tree}");

            List<DiffEntry> diffs = git.diff()
                    .setOldTree(DiffUtils.prepareTreeParser(repository, oldTree))
                    .setNewTree(DiffUtils.prepareTreeParser(repository, newTree))
                    .call();

            for (DiffEntry entry : diffs) {
                FileDiff fileDiff = new FileDiff(entry.getNewPath());
                DiffEntry.ChangeType gitChangeType = entry.getChangeType();

                DiffFormatter formatter = new DiffFormatter(new ByteArrayOutputStream());
                formatter.setRepository(repository);
                FileHeader fileHeader = formatter.toFileHeader(entry);

                for (HunkHeader hunk : fileHeader.getHunks()) {
                    for (Edit edit : hunk.toEditList()) {
                        if (edit.getType() == Edit.Type.INSERT) {
                            fileDiff.addLineChange(new LineChange(
                                    com.codebase.time_traveller.explanation.ChangeType.LINE_ADDED,
                                    edit.toString()
                            ));
                        }

                        if (edit.getType() == Edit.Type.DELETE) {
                            fileDiff.addLineChange(new LineChange(
                                    com.codebase.time_traveller.explanation.ChangeType.LINE_REMOVED,
                                    edit.toString()
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
