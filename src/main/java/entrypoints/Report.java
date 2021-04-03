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
/*
    private void selectDataFromDatabase() throws SQLException {
        List<String> params = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ROUND(AVG(value),2) FROM price ");
        if (fuelType != null) {
            sb.append("JOIN fuel ON price.fuel_id = fuel.id ");
        }
        if (petrolStation != null || cityName != null) {
            sb.append("JOIN petrolStation ON price.petrolStation_id = petrolStation.id ");
        }
        sb.append("WHERE date = ?");
        params.add(period);

        if (fuelType != null) {
            sb.append(" AND fuel.type=?");
            params.add(fuelType);
        }
        if (petrolStation != null) {
            sb.append(" AND petrolStation.name=?");
            params.add(petrolStation);
        }
        if (cityName != null) {
            sb.append(" AND petrolStation.city=?");
            params.add(cityName);
        }

        Date date = Date.valueOf(period);
        PreparedStatement stmt = DBconnect.con.prepareStatement(sb.toString());

        StringBuilder output = new StringBuilder();
        output.append("Calculate average price for ");
        for (int i = 0; i < params.size(); i++) {
            if (i == 0) {
                stmt.setDate(i + 1, date);
                output.append(period);
            } else {
                stmt.setString(i + 1, params.get(i));
                output.append(", ").append(params.get(i));
            }
        }
        System.out.println(output.toString().trim());
        System.out.print("Average price: ");
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getDouble("ROUND(AVG(value),2)") + "$");
        }
    }


 */
}
