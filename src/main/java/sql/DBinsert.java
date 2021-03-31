package sql;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import structure.*;
import parsingXML.*;

public class DBinsert {
    public static int idPetrolStation = 0;
    public static int idFuel = 0;

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement stmt = null;
        con = DBconnect.connectDB();
        try {
            File file = new File("C:\\Users\\Asus\\Desktop\\demo\\filesTest");
            List<PetrolStations> allPetrolStations = parserJAXB.unmarshal(file);
            insertToFuelTable(con, allPetrolStations);
            for (PetrolStations p : allPetrolStations) {
                for (PetrolStation ps : p.getPetrolStationList()) {
                    insertToPetrolStationTable(con, ps);
                    insertToPriceTable(con, p, ps);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void insertToPriceTable(Connection con, PetrolStations p, PetrolStation ps) throws SQLException {
        PreparedStatement stmt;
        for (Fuel f : ps.getFuels()) {
            idPetrolStation = getIdPetrolStation(con, ps);
            idFuel = getIdFuel(con, f);
            String dateString = p.getDate();
            Date date = Date.valueOf(dateString);
            Double priceValue = f.getPrice();
            String sqlPrice = "INSERT into mydatabase1.price(value,date,petrolStation_id,fuel_id) VALUES(?,?,?,?)";
            stmt = con.prepareStatement(sqlPrice);
            stmt.setDouble(1, priceValue);
            stmt.setDate(2, date);
            stmt.setInt(3, idPetrolStation);
            stmt.setInt(4, idFuel);
            stmt.executeUpdate();
            stmt.close();
        }
    }

    private static void insertToPetrolStationTable(Connection con, PetrolStation ps) throws SQLException {
        PreparedStatement stmt;
        String city = ps.getCity();
        String namePetrolStation = ps.getName();
        String address = ps.getAddress();
        String sqlPetrolStation = "INSERT into mydatabase1.petrolStation(name,address,city) VALUES(?,?,?)";
        stmt = con.prepareStatement(sqlPetrolStation);
        stmt.setString(1, namePetrolStation);
        stmt.setString(2, address);
        stmt.setString(3, city);
        stmt.executeUpdate();
        stmt.close();
    }

    private static void insertToFuelTable(Connection con, List<PetrolStations> allPetrolStations) throws SQLException {
        PreparedStatement stmt;
        ArrayList<Fuel> fuels = allPetrolStations.get(0).getPetrolStationList().get(0).getFuels();
        for (Fuel fuel : fuels) {
            String type = fuel.getType();
            String sqlFuel = "INSERT into mydatabase1.fuel(type) VALUES(?)";
            stmt = con.prepareStatement(sqlFuel);
            stmt.setString(1, type);
            stmt.executeUpdate();
            stmt.close();
        }
        System.out.println("Add data in fuel table.");
    }

    static int getIdPetrolStation(Connection con, PetrolStation ps) throws SQLException {
        String sql = "SELECT id FROM mydatabase1.petrolstation WHERE name=? AND address=? AND city=? LIMIT 1";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, ps.getName());
        stat.setString(2, ps.getAddress());
        stat.setString(3, ps.getCity());
        ResultSet rs = stat.executeQuery();
        while (rs.next()) {
            idPetrolStation = rs.getInt("id");
        }
        return idPetrolStation;
    }

    static int getIdFuel(Connection con, Fuel f) throws SQLException {
        String sql = "SELECT id FROM mydatabase1.fuel WHERE type=? LIMIT 1";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, f.getType());
        ResultSet rs = stat.executeQuery();
        while (rs.next()) {
            idFuel = rs.getInt("id");
        }
        return idFuel;
    }
}
