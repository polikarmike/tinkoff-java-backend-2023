package edu.project3.reporter;

import edu.project3.statistics.AbstractLogStatistics;

public interface ReportBuilder {
    void appendSection(StringBuilder report, String sectionTitle, AbstractLogStatistics statistics, int limit);
}
