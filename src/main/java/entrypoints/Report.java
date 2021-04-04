package entrypoints;

import com.beust.jcommander.JCommander;
import commands.ReportCommands;
import repository.ReportRepository;
import java.sql.SQLException;


public class Report {
    public static void main(String... args) throws SQLException {
        ReportCommands reportCommands = new ReportCommands();
        JCommander.newBuilder()
                .addObject(reportCommands)
                .build()
                .parse(args);
        ReportRepository reportRepository=new ReportRepository();
        reportRepository.run(reportCommands);
    }
}
