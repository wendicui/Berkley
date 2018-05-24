

public class Balloon {
    private int maxPressure = 24;
    private int currentPressure = 0;
    Balloon(){};

    public void inflate()
                throws BomException
    {
        if(this.currentPressure > (this.maxPressure - 10))
            throw new BomException();
        else{
            this.currentPressure = this.currentPressure + 10 ;
            System.out.println("Inflating");}
    }

    public static void main (String args[]){

        Balloon b = new Balloon();

        try {
            b.inflate();
            b.inflate();
            b.inflate();
        }catch (BomException bb){

        }
    }


}


class BomException extends Exception{
    public BomException(){
        System.out.println("BOM!");
    }
}