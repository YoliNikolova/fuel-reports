
import javax.xml.bind.annotation.*;
import java.util.ArrayList;


@XmlRootElement(name = "petrolStation")
public class PetrolStation {

    private String name;
    private String address;
    private String city;
    private ArrayList<Fuel> fuels;

    public PetrolStation() {
    }

    public PetrolStation(String name, String address, String city, ArrayList<Fuel> fuels) {
        this.name=name;
        this.address=address;
        this.city=city;
        this.fuels = fuels;
    }

    @XmlAttribute
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlAttribute
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper
    @XmlElement(name = "fuel")
    public ArrayList<Fuel> getFuels() {
        if (this.fuels == null) {
            this.fuels = new ArrayList<Fuel>();
        }
        return fuels;
    }

    public void setFuels(ArrayList<Fuel> fuels) {
        this.fuels = fuels;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName()).append(System.lineSeparator());
        for (Fuel fuel : this.getFuels()) {
            sb.append(fuel.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
