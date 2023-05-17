import java.util.Random;
public class RentingTranscaction<T,V> {
    private T car;
    V customer;
    RentingTranscaction(T car, V customer){
        this.car=car;
        this.customer=customer;
    }
    T getCar(){
        return car;
    }
    void setCar(T car){
        this.car=car;
    }
    V getCustomer(){
        return customer;
    }
}
