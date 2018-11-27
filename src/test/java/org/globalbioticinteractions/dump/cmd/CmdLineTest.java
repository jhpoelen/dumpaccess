package org.globalbioticinteractions.dump.cmd;

import com.beust.jcommander.MissingCommandException;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class CmdLineTest {

    @Before
    public void deleteTmpCache() {
        FileUtils.deleteQuietly(new File("target/tmp-datasets"));
    }

    @Test
    public void check() throws Throwable {
        CmdLine.run(new String[]{"tables", "-d", "somefile.accdb"});
    }

    @Test(expected = MissingCommandException.class)
    public void invalidCommand() throws Throwable {
        CmdLine.run(new String[]{"bla", "globalbioticinteractions/template-dataset"});
    }
}