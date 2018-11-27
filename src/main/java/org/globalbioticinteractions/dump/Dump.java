package org.globalbioticinteractions.dump;

/*
    Dumper - a GloBI commandline tool to help dump access databases.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eol.globi.Version;
import org.globalbioticinteractions.dump.cmd.CmdLine;

import static java.lang.System.exit;

public class Dump {
    private static final Log LOG = LogFactory.getLog(Dump.class);

    public static void main(String[] args) {
        try {
            LOG.info(Version.getVersionInfo(Dump.class));
            CmdLine.run(args);
            exit(0);
        } catch (Throwable t) {
            exit(1);
        }
    }
}
