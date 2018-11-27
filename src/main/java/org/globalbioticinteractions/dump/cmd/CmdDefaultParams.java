package org.globalbioticinteractions.dump.cmd;

import com.beust.jcommander.Parameter;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

abstract class CmdDefaultParams implements Runnable {

    @Parameter(names = {"--db-file", "-d"}, description = "database file", required = true)
    private String dbFilepath;

    @Parameter(names = {"--limit", "-n"}, description = "limit number of rows")
    private Long limit = null;

    private String getDbFilepath() {
        return dbFilepath;
    }

    public Database getDatabase() throws IOException {
        File mdbFile = new File(getDbFilepath());
        if (!mdbFile.exists()) {
            throw new IOException("failed to find [" + getDbFilepath() + "]");
        }
        return DatabaseBuilder.open(mdbFile);
    }

    public Long getLimit() {
        return limit;
    }
}
