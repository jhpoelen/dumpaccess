package org.globalbioticinteractions.dump.cmd;

import com.beust.jcommander.Parameters;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Set;

@Parameters(separators = "= ", commandDescription = "List Available Tables")
public class CmdTables extends CmdDefaultParams {

    @Override
    public void run() {
        run(System.out);
    }

    public void run(PrintStream out) {
        try {
            Set<String> tableNames = getDatabase().getTableNames();
            out.println(StringUtils.join(tableNames, "\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
