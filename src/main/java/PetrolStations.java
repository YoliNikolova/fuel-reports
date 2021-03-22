import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
public class PetrolStations implements Serializable {

    private String date;
    private List<PetrolStation> petrolStationList;

    public PetrolStations(){ }

    public PetrolStations(String date,List<PetrolStation> petrolStationList) {
        super();
        this.date = date;
        this.petrolStationList = petrolStationList;
    }

    @XmlElement
    public List<PetrolStation> getPetrolStationList() {
        return petrolStationList;
    }

    @XmlAttribute
    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getDate()).append(System.lineSeparator());
//...
        return sb.toString().trim();
    }
}
