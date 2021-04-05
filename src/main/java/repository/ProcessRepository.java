package repository;

import sql.DBconnect;
import xml.models.Fuel;
import xml.models.PetrolStation;
import xml.models.PetrolStations;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcessRepository {
    private int idPetrolStation;
    private int idFuel;
    private String configLocalDir;

    public ProcessRepository(){
        this.idPetrolStation = -1;
        this.idFuel = -1;
    }

    private void setIdFuel(int idFuel) {
        this.idFuel = idFuel;
    }

    private void setIdPetrolStation(int idPetrolStation) {
        this.idPetrolStation = idPetrolStation;
    }

    public void insertToPriceTable(PetrolStations p, PetrolStation ps) throws SQLException {
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

    public void insertToPetrolStationTable(PetrolStation ps) throws SQLException {
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

    public void insertToFuelTable(PetrolStation ps) throws SQLException {
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

    public int getIdPetrolStation(PetrolStation ps) throws SQLException {
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


    public String selectConfigLocalDir() throws SQLException {
        String sql = "SELECT configFolder FROM config LIMIT 1";
        PreparedStatement stmt = DBconnect.con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            this.configLocalDir = rs.getString("configFolder");
        }
        return this.configLocalDir;
    }
}
