package org.globalbioticinteractions.dump.cmd;

import com.beust.jcommander.JCommander;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CmdLine {
    private final static Log LOG = LogFactory.getLog(CmdLine.class);

    public static void run(JCommander actual) {
        if (null != actual && actual.getObjects().get(0) instanceof Runnable) {
            ((Runnable) actual.getObjects().get(0)).run();
        }
    }

    public static void run(String[] args) throws Throwable {
        JCommander jc = new CmdLine().buildCommander();
        try {
            jc.parse(args);
            CmdLine.run(jc.getCommands().get(jc.getParsedCommand()));
        } catch (Throwable ex) {
            LOG.error("unexpected exception", ex);
            StringBuilder out = new StringBuilder();
            jc.usage(out);
            System.err.append(out.toString());
            throw ex;
        }
    }

    public class CommandMain implements Runnable {

        @Override
        public void run() {

        }
    }

    JCommander buildCommander() {
        return JCommander.newBuilder()
                .addObject(new CommandMain())
                .addCommand("tables", new CmdTables())
                .addCommand("dump-wide", new CmdDumpWide())
                .addCommand("dump-long", new CmdDumpLong())
                .addCommand("version", new CmdVersion())
                .build();
    }


}