package repository;

import commands.ReportCommands;
import sql.DBconnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportRepository implements BaseRepository<ReportCommands> {
    @Override
    public void run(ReportCommands commands) throws SQLException {
        System.out.print(selectDataFromDatabase(commands));
        System.out.println("$");
    }

    private Double selectDataFromDatabase(ReportCommands commands) throws SQLException {
        Double avgPrice = 0.0;
        String[] periodArray = commands.getPeriod().split("-");
        List<String> params = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT ROUND(AVG(value),2) FROM price ");
        if (commands.getFuelType() != null) {
            sb.append("JOIN fuel ON price.fuel_id = fuel.id ");
        }
        if (commands.getPetrolStation() != null || commands.getCityName() != null) {
            sb.append("JOIN petrolStation ON price.petrolStation_id = petrolStation.id ");
        }

        if (periodArray.length == 3) {
            sb.append("WHERE convert(date,char(20)) LIKE '" + commands.getPeriod() + "' ");
        } else{
            sb.append("WHERE convert(date,char(20)) LIKE '" + commands.getPeriod() + "%'");
        }

        if (commands.getFuelType() != null) {
            sb.append(" AND fuel.type=?");
            params.add(commands.getFuelType());
        }
        if (commands.getPetrolStation() != null) {
            sb.append(" AND petrolStation.name=?");
            params.add(commands.getPetrolStation());
        }
        if (commands.getCityName() != null) {
            sb.append(" AND petrolStation.city=?");
            params.add(commands.getCityName());
        }

        PreparedStatement stmt = DBconnect.con.prepareStatement(sb.toString());

        StringBuilder output = new StringBuilder();
        output.append("Calculate average price for ").append(commands.getPeriod());
        for (int i = 0; i < params.size(); i++) {
            stmt.setString(i + 1, params.get(i));
            output.append(", ").append(params.get(i));
        }

        System.out.println(output.toString().trim());
        System.out.print("Average price: ");
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            avgPrice = resultSet.getDouble("ROUND(AVG(value),2)");
        }
        return avgPrice;
    }
}
