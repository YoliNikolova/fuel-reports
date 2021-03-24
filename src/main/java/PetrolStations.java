import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name="petrolStations")
public class PetrolStations{

    private String date;
    private ArrayList<PetrolStation> petrolStationList;

    public PetrolStations(){ }

    public PetrolStations(String date,ArrayList<PetrolStation> petrolStationList) {
        this.date = date;
        this.petrolStationList = petrolStationList;
    }

    @XmlElement(name="petrolStation")
    public ArrayList<PetrolStation> getPetrolStationList() {
        if(this.petrolStationList==null){
            this.petrolStationList = new ArrayList<PetrolStation>();
        }
        return this.petrolStationList;
    }

    public void setPetrolStationList(ArrayList<PetrolStation> petrolStationList) {
        this.petrolStationList = petrolStationList;
    }

    @XmlAttribute
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getDate()).append(System.lineSeparator());
        return sb.toString().trim();
    }
}
