package org.globalbioticinteractions.dump.cmd;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Parameters(separators = "= ", commandDescription = "Dump database table to wide tsv (one line per table row)")
public class CmdDumpWide extends CmdDefaultParams {

    @Parameter(description = "table", required = true)
    private String tableName;

    @Override
    public void run() {
        run(System.out);
    }

    public void run(PrintStream out) {
        try {
            Table table = getDatabase().getTable(getTableName());
            List<? extends Column> columns = table.getColumns();
            out.println(columns.stream().map(Column::getName).collect(Collectors.joining("\t")));

            Row row;
            long count = 0L;
            while ((getLimit() == null || count < getLimit()) && (row = table.getNextRow()) != null) {
                out.println(row.values().stream().map(value -> value == null ? "" : value.toString()).collect(Collectors.joining("\t")));
                count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTableName() {
        return tableName;
    }
}


