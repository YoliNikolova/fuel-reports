package sql;

import java.io.File;
import java.sql.*;
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

    private void setIdFuel(int idFuel) {
        this.idFuel = idFuel;
    }

    private void setIdPetrolStation(int idPetrolStation) {
        this.idPetrolStation = idPetrolStation;
    }

    private void insertData() {
        try {
            File file = new File(DBSelect.selectConfigLocalDir());
            List<PetrolStations> allPetrolStations = JAXBparser.unmarshal(file);
            for (PetrolStations p : allPetrolStations) {
                for (PetrolStation ps : p.getPetrolStationList()) {
                    insertToFuelTable(ps);
                    insertToPetrolStationTable(ps);
                    insertToPriceTable(p, ps);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void insertToPriceTable(PetrolStations p, PetrolStation ps) throws SQLException {
        for (Fuel fuel : ps.getFuels()) {
            this.setIdPetrolStation(getIdPetrolStation(ps));
            this.setIdFuel(getIdFuel(fuel));
            String dateString = p.getDate();
            Date date = Date.valueOf(dateString);
            Double priceValue = fuel.getPrice();
            String insertToPriceTable = "INSERT into price(value,date,petrolStation_id,fuel_id) VALUES(?,?,?,?)";
            PreparedStatement stmt = DBconnect.con.prepareStatement(insertToPriceTable);
            stmt.setDouble(1, priceValue);
            stmt.setDate(2, date);
            stmt.setInt(3, this.idPetrolStation);
            stmt.setInt(4, this.idFuel);
            stmt.executeUpdate();
            stmt.close();
        }
    }

    private void insertToPetrolStationTable(PetrolStation ps) throws SQLException {
        String city = ps.getCity();
        String namePetrolStation = ps.getName();
        String address = ps.getAddress();
        String insertToPS = "INSERT into petrolStation(name,address,city) VALUES(?,?,?)";
        PreparedStatement stmt = DBconnect.con.prepareStatement(insertToPS);
        stmt.setString(1, namePetrolStation);
        stmt.setString(2, address);
        stmt.setString(3, city);
        stmt.executeUpdate();
        stmt.close();

    }

    private void insertToFuelTable(PetrolStation ps) throws SQLException {
        for (Fuel fuel : ps.getFuels()) {
            String type = fuel.getType();
            String insertToFuelTable = "INSERT into fuel(type) VALUES(?) ON DUPLICATE KEY UPDATE type= ? ";
            PreparedStatement stmt = DBconnect.con.prepareStatement(insertToFuelTable);
            stmt.setString(1, type);
            stmt.setString(2, type);
            stmt.executeUpdate();
            stmt.close();
        }
    }

    private int getIdPetrolStation(PetrolStation ps) throws SQLException {
        String getId = "SELECT id FROM petrolstation WHERE name=? AND address=? AND city=? LIMIT 1";
        PreparedStatement stmt = DBconnect.con.prepareStatement(getId);
        stmt.setString(1, ps.getName());
        stmt.setString(2, ps.getAddress());
        stmt.setString(3, ps.getCity());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            this.setIdPetrolStation(rs.getInt("id"));
        }
        return this.idPetrolStation;
    }

    private int getIdFuel(Fuel f) throws SQLException {
        String getId = "SELECT id FROM fuel WHERE type=? LIMIT 1";
        PreparedStatement stmt = DBconnect.con.prepareStatement(getId);
        stmt.setString(1, f.getType());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            this.setIdFuel(rs.getInt("id"));
        }
        return this.idFuel;
    }
}

