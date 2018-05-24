
class Register {
    private double total;
    static int num;
    private int transac;

    //create a cash register
    public Register(){
        total = 0.0;
        transac = 0;
        num ++;
    }

    //add to transaction
    public void AddTansaction( double amount){
        total += amount;
        transac ++;
        System.out.println(amount + " is added to the account");
        System.out.println("Your total is now " + this.getTotal());
    }

    //return the number of transactions received
    public int getTransac(){
        System.out.println("There are altogether " + transac + " transactions for " + this);
        return transac;
    }

    //return the number of register created
    public int getNum() {
        System.out.println("There are altogether " + num + " registers created");
        return num;
    }

    //return the total amount
    public double getTotal(){
        return total;
    }

    //reset a cashregiste
    public void resetRegister(){
        total = 0.0;
        transac = 0;
        System.out.println(this + " register is reset");
    }

    public void printDivider(){
        System.out.println( "_______________________________");
    }
}

public class cashRegister{
    public static void main(String args[]){
        //create new register instance
        Register reg1 = new Register();
        Register reg2 = new Register();

        //check add transaction function
        reg1.AddTansaction(10.21);
        reg1.printDivider();

        //check other member functions
        reg2.AddTansaction(20.21);
        reg2.AddTansaction(-10.01);
        reg2.getTransac();
        reg2.printDivider();

        //check static function
        reg2.getNum();
        reg2.resetRegister();
        reg2.getNum();
        reg2.getTransac();

    }
}

