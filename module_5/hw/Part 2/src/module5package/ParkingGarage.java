package module5package;

public class ParkingGarage {
    private int carNumLimit, leftCarCap = 20;
    private int weightLimit, leftWeightCap = 25000;

    private String main(){
        String result = "left car capacity" + leftCarCap + "\n"
                +"left weight capacity" + leftWeightCap;

        // check full condition
        if(leftWeightCap < 1000 || leftCarCap == 0){
            return "Parking Lot is full";
        }

        return result;
    }

    protected void park(Vehicle newCar, int num){
        // create variable to check if this will exceed its limit
        int carCapAfter = leftCarCap - num;
        int weightCapAfter = leftWeightCap - newCar.getWeightInPounds() * num;

        //if there is capacily left, park car
        if ((carCapAfter > 0)
                && (weightCapAfter > 0)){
            leftCarCap--;
            leftWeightCap = weightCapAfter;
        }else if ((carCapAfter > 0)
                && (weightCapAfter <0)){
            System.out.println("Your ride is too heavy for now");
        }

        // report left capacity
        System.out.println(main());
    }
}
