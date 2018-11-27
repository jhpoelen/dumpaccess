package org.globalbioticinteractions.dump.cmd;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Parameters(separators = "= ", commandDescription = "Dump database to long tsv (one line per table/row/column value).")
public class CmdDumpLong extends CmdDefaultParams {
    private final static Log LOG = LogFactory.getLog(CmdDumpLong.class);

    public CmdDumpLong() {
    }

    @Override
    public void run() {
        try {
            Set<String> tableNames = getDatabase().getTableNames();
            System.out.println(StringUtils.join(Arrays.asList("table", "row", "column", "value"), "\t"));
            for (String tableName : tableNames) {
                Table table = getDatabase().getTable(tableName);
                List<? extends Column> columns = table.getColumns();
                Row row;
                long count = 0L;
                while ((getLimit() == null || count < getLimit()) && (row = table.getNextRow()) != null) {
                    for (Column column : columns) {
                        Object value = row.get(column.getName());
                        String valueSeq = StringUtils.join(Arrays.asList(tableName, row.getId(), column.getName(), value), "\t");
                        System.out.println(valueSeq);
                    }
                    count++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
