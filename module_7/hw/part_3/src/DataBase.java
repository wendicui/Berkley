public class DataBase {
    private Integer data = null;
    //create object to control conditions
    private boolean changed = false;
    private boolean printed = false;

    public DataBase(){
        //create reading thread
        Runnable reader = new Runnable() {
            @Override
            public void run() {
                for(;;){
                    read();
                }
            }
        };
        //create adding thread

        Runnable adder = new Runnable() {
            @Override
            public void run() {
                for(;;){
                    add();
                }
            }
        };
        new Thread(reader).start();
        new Thread(adder).start();
    }

//add method
    synchronized void add(){
        try{
            while(data != null && printed){
                if(data <10) {
                    data = data + 1;
                    printed = false;
                    notify();
                    wait();
                }
            }
        }catch(InterruptedException e ){}
        // data is null
        data = 1;
        notify();
        changed = true;
    }

//read method
    synchronized void read(){
        try{
            while(data == null){
                System.out.print("Please add data \n");
                notify();
                wait();
            }
        }catch(InterruptedException e ){}

       try {
           while (changed) {
               System.out.print("Current Number is " + this.data + "\n");
               printed = true;
               notify();
               wait();
           }
       } catch(InterruptedException e){};
    }

    public static void main (String args []){
        DataBase b = new DataBase();

    }

}
