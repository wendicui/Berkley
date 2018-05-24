package module5package;

// all the interface
interface Domestic { }
interface Import { }
interface Japanese extends Import { }
interface German extends Import { }
interface Detroit extends Domestic { }
interface SpringHill extends Domestic { }

interface Vehicle {
    int getWeightInPounds( );
}

interface Automobile extends Vehicle { }
interface LargeAutomobile extends Vehicle { }
interface Sedan extends Automobile { }
interface Van extends LargeAutomobile { }
interface Truck extends LargeAutomobile { }
interface Compact extends Automobile { }
interface SportsUtilityVehicle extends Truck, Van { }


// all the classes


//super class- small car
class smallCar implements Vehicle {
    private int weight = 1000;
    public int getWeightInPounds( ){
        return  weight;
    };
}

//super class - large car
class largeCar implements Vehicle {
    private int weight = 2500;
    public int getWeightInPounds( ){
        return  weight;
    };
}

//subclasses
class SaturnSL1 extends smallCar implements SpringHill, Sedan { }
class HondaCivic extends smallCar implements Japanese, Compact { }
class MercedesC230 extends smallCar implements German, Sedan { }
class ChevyS10 extends largeCar implements Detroit, Truck { }
class SubaruOutback extends smallCar implements Japanese, SportsUtilityVehicle { }


