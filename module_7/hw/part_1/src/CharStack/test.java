package CharStack;

public class test {
    test(){};

    public static void main (String args[]){
        CharStack newchar = new CharStack(5);


        char ltr = "a".charAt(0);
        newchar.push(ltr);

        try{
            newchar.pop();
            newchar.pop();
        }catch(UnderflowException efe){
            System.out.println(efe.getMsg());
        }


    }
}
