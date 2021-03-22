import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Fuel implements Serializable {
    private String type;
    private double price;

    public Fuel(){ }

    public Fuel(String type, double price) {
        super();
        this.type = type;
        this.price = price;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Fuel type: %s, price: %.2f", this.getType(), this.getPrice());
    }
}
