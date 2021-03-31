package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBcreate {

    public DBcreate() {
        this.createTables();
    }

    private void createTables() {
        try {
            Connection con = DBconnect.connectDB();
            createPetrolStationTable(con);
            createFuelTable(con);
            createPriceTable(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void createPetrolStationTable(Connection con) throws SQLException {
        String createPetrolStationTable = "CREATE TABLE IF NOT EXISTS `petrolStation` (\n" +
                "  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `address` VARCHAR(45) NULL,\n" +
                "  `city` VARCHAR(45) NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB;";
        PreparedStatement stmt = con.prepareStatement(createPetrolStationTable);
        stmt.execute();
        stmt.close();
        System.out.println("Table for petrolStation is created.");
    }

    private void createFuelTable(Connection con) throws SQLException {
        String createFuelTable = "CREATE TABLE IF NOT EXISTS `fuel` (\n" +
                "  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                "  `type` VARCHAR(45) NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,\n" +
                "  UNIQUE INDEX `type_UNIQUE` (`type` ASC) VISIBLE)\n" +
                "ENGINE = InnoDB;";
        PreparedStatement stmt = con.prepareStatement(createFuelTable);
        stmt.execute();
        stmt.close();
        System.out.println("Table for fuel is created.");
    }

    private void createPriceTable(Connection con) throws SQLException {
        String createPriceTable = "CREATE TABLE IF NOT EXISTS `price` (\n" +
                "  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                "  `value` DOUBLE NULL,\n" +
                "  `date` DATE NULL,\n" +
                "  `petrolStation_id` INT UNSIGNED NOT NULL,\n" +
                "  `fuel_id` INT UNSIGNED NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  INDEX `fk_price_petrolStation_idx` (`petrolStation_id` ASC) VISIBLE,\n" +
                "  INDEX `fk_price_fuel1_idx` (`fuel_id` ASC) VISIBLE,\n" +
                "  CONSTRAINT `fk_price_petrolStation`\n" +
                "    FOREIGN KEY (`petrolStation_id`)\n" +
                "    REFERENCES `petrolStation` (`id`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT `fk_price_fuel1`\n" +
                "    FOREIGN KEY (`fuel_id`)\n" +
                "    REFERENCES `fuel` (`id`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION)\n" +
                "ENGINE = InnoDB;";
        PreparedStatement stmt = con.prepareStatement(createPriceTable);
        stmt.execute();
        stmt.close();
        System.out.println("Table for price is created.");
    }
}
