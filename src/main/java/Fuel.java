import javax.xml.bind.annotation.*;


public class Fuel {

    private String type;
    private double price;

    public Fuel(){ }

    public Fuel(String type, double price) {
       this.type=type;
        this.price=price;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name="price")
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