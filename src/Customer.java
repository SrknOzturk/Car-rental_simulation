import java.text.DecimalFormat;
import java.util.Random;
public class Customer {
    private double qualityThreshold;
    private String customerId;
    Customer(int id){
        Double randomNumber= (new Random().nextDouble()*2)+1;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        this.qualityThreshold = Double.parseDouble(decimalFormat.format(randomNumber));
        this.customerId=Integer.toString(id);
    }
    double getQualityThreshold(){
        return qualityThreshold;
    }
    String getCustomerId(){
        return customerId;
    }
    void setTreshould(){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        qualityThreshold = Double.parseDouble(decimalFormat.format(qualityThreshold*0.9));
    }
}
