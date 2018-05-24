package Generator;
import java.util.*;

//create object that can store both ArrayList and Scores

public class Transfer {
    private int score = 0;
    private ArrayList<Job> shrinkedList;

    public Transfer(int num, ArrayList<Job>  list){
        this.score = num;
        this.shrinkedList = list;
    }

    protected int returnScore (){
        return score ;
    }

    protected ArrayList<Job> returnList(){
        return shrinkedList;
    }
}
