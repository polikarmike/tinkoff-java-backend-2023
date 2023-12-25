package edu.project3.statistics;

import edu.project3.type.LogRecord;
import java.util.List;

public abstract class AbstractLogStatistics {
    protected List<LogRecord> logRecords;

    public AbstractLogStatistics(List<LogRecord> logRecords) {
        this.logRecords = logRecords;
    }

    public abstract String toMarkdown(int limit);

    public abstract String toAsciiDoc(int limit);
}
