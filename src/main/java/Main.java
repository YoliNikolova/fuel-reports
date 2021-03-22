import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\Asus\\IdeaProjects\\XMLparse\\src\\main\\java\\file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(PetrolStations.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            PetrolStations p = (PetrolStations) jaxbUnmarshaller.unmarshal(file);
            System.out.println(p.getDate());
        }catch (JAXBException e){
            e.printStackTrace();
        }

    }
}
