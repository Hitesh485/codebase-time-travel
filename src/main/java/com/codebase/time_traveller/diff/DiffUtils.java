package com.codebase.time_traveller.diff;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

public class DiffUtils {
    public static CanonicalTreeParser prepareTreeParser(
            Repository repository,
            ObjectId treeId
    ) throws Exception {

        try (ObjectReader reader = repository.newObjectReader()) {
            CanonicalTreeParser parser = new CanonicalTreeParser();
            parser.reset(reader, treeId);
            return parser;
        }
    }
}
