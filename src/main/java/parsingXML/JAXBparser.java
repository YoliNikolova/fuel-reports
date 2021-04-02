package parsingXML;

import structure.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;



public final class JAXBparser {
    public static List<PetrolStations> unmarshal(File file) {
        List<PetrolStations> listToReturn = new ArrayList<>();
        try {
            File[] allFiles = file.listFiles();
            for (File f : allFiles) {
                JAXBContext jaxbContext = JAXBContext.newInstance(PetrolStations.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                PetrolStations p = (PetrolStations) jaxbUnmarshaller.unmarshal(f);
                listToReturn.add(p);
            }
            return listToReturn;
        } catch (JAXBException e) {
            System.out.println();
            return null;
        }
    }
}
