package Generator;
import java.util.*;

//class that create object that can list all the possible sequences of an input ArrayList.

public class Shuffle {

    public ArrayList<ArrayList<Job>> Shuffle(ArrayList<Job> al){

        // ArrayList that store all the possible sequences
        ArrayList<ArrayList<Job>> solutions = new ArrayList<ArrayList<Job>>();

        int size = al.size();

        if( size == 1){
            solutions.add(al);
        }else {
            //insert the first element to all possible locations to the results of recursive calls
            Object item = al.get(0);
            ArrayList<Job> rep = new ArrayList<Job> (al);
            rep.remove(0);

            //recursive call
            ArrayList<ArrayList<Job>> descend = Shuffle(rep);

            for(ArrayList arr: descend){

                for(int i = 0; i < size; i++){
                    ArrayList baseArr = new ArrayList (arr);
                    baseArr.add(i,item);
                    solutions.add(baseArr);
                }
            }
        }

        return solutions;
    }


    public static void main(String[] args) {
        ArrayList test = new ArrayList();
        test.add(1);
        test.add(3);
        test.add(9);
        test.add(7);

        Shuffle shuf = new Shuffle();

        System.out.println(shuf.Shuffle(test).size());
        //System.out.println(shuf.solutions);

    }
}
