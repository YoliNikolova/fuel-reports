import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AdapterPrice extends XmlAdapter<String,Double> {
    @Override
    public Double unmarshal(String s) throws Exception{
        if(s.contains("$")){
            return Double.parseDouble(s.substring(1));
        }else{
            return Double.parseDouble(s);
        }
    }

    @Override
    public String marshal(Double d) throws Exception{
        return null;
    }
}
