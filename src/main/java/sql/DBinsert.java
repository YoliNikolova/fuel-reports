package sql;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import structure.*;
import parsingXML.*;

public class DBinsert {
    private int idPetrolStation;
    private int idFuel;

    public DBinsert() {
        this.idPetrolStation = -1;
        this.idFuel = -1;
        this.insertData();
    }

    public int getIdFuel() {
        return idFuel;
    }

    public int getIdPetrolStation() {
        return idPetrolStation;
    }

    private void setIdFuel(int idFuel) {
        this.idFuel = idFuel;
    }

    private void setIdPetrolStation(int idPetrolStation) {
        this.idPetrolStation = idPetrolStation;
    }

    private void insertData() {
        Connection con = DBconnect.connectDB();
        try {
            File file = new File("C:\\Users\\Asus\\Desktop\\demo\\allFiles");
            List<PetrolStations> allPetrolStations = JAXBparser.unmarshal(file);
            for (PetrolStations p : allPetrolStations) {
                for (PetrolStation ps : p.getPetrolStationList()) {
                    insertToFuelTable(con, ps.getFuels());
                    insertToPetrolStationTable(con, ps);
                    insertToPriceTable(con, p, ps);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void insertToPriceTable(Connection con, PetrolStations p, PetrolStation ps) throws SQLException {
        for (Fuel f : ps.getFuels()) {
            this.setIdPetrolStation(getIdPetrolStation(con, ps));
            this.setIdFuel(getIdFuel(con, f));
            String dateString = p.getDate();
            Date date = Date.valueOf(dateString);
            Double priceValue = f.getPrice();
            String sqlPrice = "INSERT into price(value,date,petrolStation_id,fuel_id) VALUES(?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sqlPrice);
            stmt.setDouble(1, priceValue);
            stmt.setDate(2, date);
            stmt.setInt(3, this.idPetrolStation);
            stmt.setInt(4, this.idFuel);
            stmt.executeUpdate();
            stmt.close();
        }
    }

    private void insertToPetrolStationTable(Connection con, PetrolStation ps) throws SQLException {
        String city = ps.getCity();
        String namePetrolStation = ps.getName();
        String address = ps.getAddress();
        String sqlPetrolStation = "INSERT into petrolStation(name,address,city) VALUES(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sqlPetrolStation);
        stmt.setString(1, namePetrolStation);
        stmt.setString(2, address);
        stmt.setString(3, city);
        stmt.executeUpdate();
        stmt.close();
    }

    private void insertToFuelTable(Connection con, ArrayList<Fuel> fuels) throws SQLException {
        for (Fuel fuel : fuels) {
            String type = fuel.getType();
            String sqlFuel = "INSERT into fuel(type) VALUES(?) ON DUPLICATE KEY UPDATE type= ? ";
            PreparedStatement stmt = con.prepareStatement(sqlFuel);
            stmt.setString(1, type);
            stmt.setString(2, type);
            stmt.executeUpdate();
            stmt.close();
        }
    }

    private int getIdPetrolStation(Connection con, PetrolStation ps) throws SQLException {
        String sql = "SELECT id FROM petrolstation WHERE name=? AND address=? AND city=? LIMIT 1";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, ps.getName());
        stmt.setString(2, ps.getAddress());
        stmt.setString(3, ps.getCity());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            this.setIdPetrolStation(rs.getInt("id"));
        }
        return this.idPetrolStation;
    }

    private int getIdFuel(Connection con, Fuel f) throws SQLException {
        String sql = "SELECT id FROM fuel WHERE type=? LIMIT 1";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, f.getType());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            this.setIdFuel(rs.getInt("id"));
        }
        return this.idFuel;
    }
}

