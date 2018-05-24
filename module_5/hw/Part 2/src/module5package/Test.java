package module5package;

public class Test {
    public static void main (String[] args){
        ParkingGarage garage = new ParkingGarage();
        SaturnSL1 car = new SaturnSL1();
        ChevyS10 chevy = new ChevyS10();

        //pretend that one car can be constantly parked in to a garage for test purpose
        garage.park(car, 1);
        garage.park(chevy,9);
        garage.park(car,1);

        //pretend one car can be parked into multile garages at same time
        ParkingGarage garage2 = new ParkingGarage();
        garage2.park(car, 1);
        garage2.park(chevy,9);
        garage2.park(chevy,1);

    }
}
