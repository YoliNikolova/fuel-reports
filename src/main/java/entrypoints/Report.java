package entrypoints;

import com.beust.jcommander.JCommander;
import commands.ReportCommands;
import repository.ReportRepository;
import services.ReportService;

import java.sql.SQLException;


public class Report {
    public static void main(String... args) throws SQLException {
        ReportCommands reportCommands = new ReportCommands();
        JCommander.newBuilder()
                .addObject(reportCommands)
                .build()
                .parse(args);
        ReportService reportService = new ReportService(new ReportRepository());
        reportService.run(reportCommands);
    }
}
