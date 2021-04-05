package sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBcreate {

    public DBcreate() {
    }

    public void createTables() {
        try {
            createPetrolStationTable();
            createFuelTable();
            createPriceTable();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void createPetrolStationTable() throws SQLException {
        String createPetrolStationTable = "CREATE TABLE IF NOT EXISTS `petrolStation` (\n" +
                "  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `address` VARCHAR(45) NULL,\n" +
                "  `city` VARCHAR(45) NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB;";
        PreparedStatement stmt = DBconnect.con.prepareStatement(createPetrolStationTable);
        stmt.execute();
        stmt.close();
        System.out.println("Table for petrolStation is created.");
    }

    private void createFuelTable() throws SQLException {
        String createFuelTable = "CREATE TABLE IF NOT EXISTS `fuel` (\n" +
                "  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                "  `type` VARCHAR(45) NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,\n" +
                "  UNIQUE INDEX `type_UNIQUE` (`type` ASC) VISIBLE)\n" +
                "ENGINE = InnoDB;";
        PreparedStatement stmt = DBconnect.con.prepareStatement(createFuelTable);
        stmt.execute();
        stmt.close();
        System.out.println("Table for fuel is created.");
    }

    private void createPriceTable() throws SQLException {
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
        PreparedStatement stmt = DBconnect.con.prepareStatement(createPriceTable);
        stmt.execute();
        stmt.close();
        System.out.println("Table for price is created.");
    }

    public void createConfigTable() throws SQLException {
        String createConfigTable = "CREATE TABLE IF NOT EXISTS `config` (\n" +
                "  `configFolder` VARCHAR(100) NOT NULL,\n" +
                "  PRIMARY KEY (`configFolder`),\n" +
                "  UNIQUE INDEX `configFolder_UNIQUE` (`configFolder` ASC) VISIBLE) ENGINE = InnoDB;";
        PreparedStatement stmt = DBconnect.con.prepareStatement(createConfigTable);
        stmt.execute();
        stmt.close();
    }
}
