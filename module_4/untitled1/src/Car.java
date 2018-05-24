//interface Domestic { }
//interface Import { }
//interface Japanese extends Import { }
//interface German extends Import { }
//interface Detroit extends Domestic { }
//interface SpringHill extends Domestic { }
//
//interface Vehicle {
//  int getWeightInPounds( );
//}
//
//interface Automobile extends Vehicle { }
//interface LargeAutomobile extends Vehicle { }
//interface Sedan extends Automobile { }
//interface Van extends LargeAutomobile { }
//interface Truck extends LargeAutomobile { }
//interface Compact extends Automobile { }
//interface SportsUtilityVehicle extends Truck, Van { }
//
////create smallCar class
//class smallCar implements Vehicle {
//    private int weight = 1000;
//    public int getWeightInPounds( ){
//        return  weight;
//    };
//}
//
////create largeCar class
//class largeCar implements Vehicle {
//    private int weight = 2500;
//    public int getWeightInPounds( ){
//        return  weight;
//    };
//}
//
//
//class SaturnSL1 extends smallCar implements SpringHill, Sedan { }
//class HondaCivic extends smallCar implements Japanese, Compact { }
//class MercedesC230 extends smallCar implements German, Sedan { }
//class ChevyS10 extends largeCar implements Detroit, Truck { }
//class SubaruOutback extends smallCar implements Japanese, SportsUtilityVehicle { }
//
//
//class ParkingGarage {
//    private int carNumLimit, leftCarCap = 20;
//    private int weightLimit, leftWeightCap = 25000;
//
//
//    private String main(){
//        String result = "left car capacity" + leftCarCap + "\n"
//                        +"left weight capacity" + leftWeightCap;
//
//        // check full condition
//        if(leftWeightCap < 1000 || leftCarCap == 0){
//            return "Parking Lot is full";
//        }
//
//        return result;
//    }
//
//    protected void park(Vehicle newCar, int num){
//        // create variable to check if this will exceed its limit
//        int carCapAfter = leftCarCap - num;
//        int weightCapAfter = leftWeightCap - newCar.getWeightInPounds() * num;
//
//        //if there is capacily left, park car
//        if ((carCapAfter > 0)
//            && (weightCapAfter > 0)){
//            leftCarCap--;
//            leftWeightCap = weightCapAfter;
//        }
//
//        // report left capacity
//        System.out.println(main());
//    }
//
//}

interface A {
    pvoid hi() { System.out.println("A"); }
}

interface B {
    default void hi() { System.out.println("A"); }
}

class AB implements A, B { // won't compile
}



public class Car {
    public static void main(String[] args) {
        AB trial = new AB();
        trial.hi();
    }
}
