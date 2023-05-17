import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter available car count, N=");
        int numberOfCar=scanner.nextInt();
        System.out.print("Enter customer count, k=");
        int numberOfCustomer=scanner.nextInt();
        ArrayDeque<Car> carDeque=new ArrayDeque<>(numberOfCar);
        ArrayQueue<Customer> customerQueue=new ArrayQueue<>(numberOfCustomer);
        AList<RentingTranscaction> transcactionList=new AList<>();
        int transactionNumber=0;
        for(int i=0;i<numberOfCar;i++){
            Car newCar=new Car(i);
            carDeque.addToBack(newCar);
        }
        for (int j=0;j<numberOfCustomer;j++){
            Customer newCustomer=new Customer(j);
            customerQueue.enqueue(newCustomer);
        }

        for(int k=0;k<6;k++){
            System.out.println("**************Day"+String.valueOf(k+1)+"**************");
            int initialNumberOfCar=numberOfCar;
            for(int y=0;y<initialNumberOfCar;y++){
                if(carDeque.isEmpty()){
                    break;
                }
                Car car=carDeque.removeFront();
                double carTreshold=car.getQualityScore();
                if(customerQueue.isEmpty()){
                    carDeque.addToFront(car);
                    break;
                }
                System.out.println("Current car"+car.getCarId()+" quality="+carTreshold+" is offering to");
                Customer customer=customerQueue.dequeue();
                double customerThreshold=customer.getQualityThreshold();
                int unexceptedCount=1;
                int initialNumberOfCustomer=numberOfCustomer;
                while (unexceptedCount<=initialNumberOfCustomer){
                    if(carTreshold>=customerThreshold){
                        System.out.println("          Current cust"+customer.getCustomerId()+" threshold="+customerThreshold+"         --accepted");
                        int occupancyLeft=new Random().nextInt(5)+1;
                        car.setOccupancyLeft(occupancyLeft);
                        RentingTranscaction<Car,Customer> transaction=new RentingTranscaction<>(car,customer);
                        transcactionList.add(transaction);
                        transactionNumber++;
                        numberOfCustomer--;
                        numberOfCar--;
                        for(int i=0;i<numberOfCustomer-unexceptedCount+1;i++){
                            customer=customerQueue.dequeue();
                            customerQueue.enqueue(customer);
                        }
                        break;
                        }
                    else{
                        System.out.println("          Current cust"+customer.getCustomerId()+" threshold="+customerThreshold+"         --not accepted");
                        customer.setTreshould();
                        unexceptedCount++;
                        customerQueue.enqueue(customer);
                        if(unexceptedCount==initialNumberOfCustomer+1){
                            carDeque.addToBack(car);
                            break;
                        }
                        customer=customerQueue.dequeue();
                        customerThreshold=customer.getQualityThreshold();
                    }
                }
            }
            System.out.println("All car have seen");
            if (customerQueue.isEmpty()) {
                System.out.println("All customers rent a car");
            } else {
                System.out.println("But there are still customers waiting.");
            }
            System.out.println("Rented cars:");
            int o=0;
            int occupancyNumberFinished=0;
            AList<Car> occupancyLeftCars=new AList<>();
            while(o<transactionNumber){
                RentingTranscaction<Car,Customer> updateTransaction=transcactionList.remove(1);
                Car updateCar= (Car) updateTransaction.getCar();
                System.out.println("Car"+updateCar.getCarId()+" by cust"+updateTransaction.getCustomer().getCustomerId()+" occupancy="+updateCar.getOccupancyLeft());
                updateCar.changeOccupancyLeft();
                if(updateCar.getOccupancyLeft()==0){
                    occupancyLeftCars.add(updateCar);
                    occupancyNumberFinished++;
                }
                else {
                updateTransaction.setCar(updateCar);
                transcactionList.add(updateTransaction);
                }
                o++;
            }
            transactionNumber-=occupancyNumberFinished;
            System.out.println("Available cars:");
            for(int i=0;i<numberOfCar;i++){
                Car availableCar=carDeque.removeFront();
                System.out.println("Car"+availableCar.getCarId());
                carDeque.addToBack(availableCar);
            }
            numberOfCar+=occupancyNumberFinished;
            for(int i=1;i<=occupancyNumberFinished;i++){
                Car occupancyLeftCar= occupancyLeftCars.remove(1);
                carDeque.addToFront(occupancyLeftCar);
            }
            System.out.println("**************End of Day**************");
        }
    }}
