import java.text.DecimalFormat;
import java.util.Random;
public class Car {
    private String carId;
    private double qualityScore;
    private int occupancyLeft=0;
    Car(int id){
        Double reelRandomNumber=(new Random().nextDouble()*2)+1;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        this.qualityScore = Double.parseDouble(decimalFormat.format(reelRandomNumber));
        this.carId=Integer.toString(id);

    }
    String getCarId(){
        return carId;
    }
    double getQualityScore(){
        return qualityScore;
    }
    int getOccupancyLeft(){return occupancyLeft;}
    void setOccupancyLeft(int occupancyLeft){
        this.occupancyLeft=occupancyLeft;
    }
    void changeOccupancyLeft(){
        occupancyLeft-=1;
    }
}
