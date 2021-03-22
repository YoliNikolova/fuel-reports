
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class PetrolStation implements  Serializable{
    private String name;
    private String address;
    private String city;
    List<Fuel> fuels;

    public PetrolStation(){ }

    public PetrolStation(String name, String address, String city,List<Fuel> fuels) {
        super();
        this.setName(name);
        this.setAddress(address);
        this.setCity(city);
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

    @XmlElement
    public List<Fuel> getFuels() {
        return fuels;
    }

    public void setFuels(List<Fuel> fuels) {
        this.fuels = fuels;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName()).append(System.lineSeparator());
        for (Fuel fuel : fuels) {
            sb.append(fuel.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
