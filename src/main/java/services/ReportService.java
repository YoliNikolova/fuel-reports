package services;

import commands.ReportCommands;
import repository.ReportRepository;

import java.sql.SQLException;

public class ReportService implements BaseService<ReportCommands> {
    private ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public void run(ReportCommands commands) throws SQLException {
        reportRepository.selectDataFromDatabase(commands);
    }
}
