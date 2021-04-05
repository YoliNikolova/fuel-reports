package services;

import commands.ProcessCommands;
import repository.ProcessRepository;
import sftp.SFTPFileTransfer;
import sql.DBcreate;
import xml.models.PetrolStation;
import xml.models.PetrolStations;
import xml.parsingXML.JAXBparser;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class ProcessService implements BaseService<ProcessCommands> {
    private ProcessRepository processRepository;

    public ProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @Override
    public void run(ProcessCommands commands) throws SQLException {
        SFTPFileTransfer.filesTransfer(commands.getLimit());
        DBcreate tables = new DBcreate();
        tables.createTables();
        getFilesAndSave();
        System.out.println("Data is inserted");
    }

    private void getFilesAndSave() {
        try {
            File file = new File(processRepository.selectConfigLocalDir());
            List<PetrolStations> allPetrolStations = JAXBparser.unmarshal(file);
            for (PetrolStations p : allPetrolStations) {
                for (PetrolStation ps : p.getPetrolStationList()) {
                    processRepository.insertToFuelTable(ps);
                    processRepository.insertToPetrolStationTable(ps);
                    processRepository.insertToPriceTable(p, ps);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
