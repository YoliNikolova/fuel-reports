package structure;

import adapters.AdapterPrice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
public class Fuel {

    @XmlAttribute(name = "type")
    private String type;

    @XmlJavaTypeAdapter(AdapterPrice.class)
    private Double price;

    public Fuel(){ }

    public Fuel(String type, double price) {
       this.setType(type);
       this.setPrice(price);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Fuel type: %s, price: %.2f", this.getType(), this.getPrice());
    }
}
