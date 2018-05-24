package Generator;
import java.lang.reflect.Array;
import java.util.*;

public class Scheduler {
    //maximum number of personnel that can be on site every day
    private int max ;
    // store the minimum days required to finish all the jobs
    private int bestScore = Integer.MAX_VALUE;
    // arrayList to store all input jobs
    protected ArrayList<Job> jobArray = new ArrayList<Job>();
    //arrayList to store all possible sequences of jobs;
    public ArrayList<ArrayList<Job>> possibleSchedule = new ArrayList<ArrayList<Job>>();
    //arrayList that holds the best solutions
    public ArrayList<ArrayList<Job>> bestSchedule = new ArrayList<ArrayList<Job>>();


    public Scheduler( int max){
        this.max = max;
    };


    protected void addJob(Job newJob){
        jobArray.add(newJob);
    }

    protected void start(){
        // permutation of all the job sequence
        this.shuffle();
        // filter the permutation result, and leave only ones that meet each job requirements
        this.filter();
        // shrink each sequence as much as possible, but meeting job requirement and field personnel requirements
        this.shrink();
        // check the solutions to get rid of duplicate solutions, since multiple jobs can start on same day;
        this.check();
        //print the results;
        this.print();
    }


    // get all the possible sequences of the input jobs, with no overlap nor break;
    protected void shuffle(){
        Shuffle shuf = new Shuffle();
        possibleSchedule = shuf.Shuffle(jobArray);
    }

    //filter the sequence that does not meet job requirements in terms of one should only start after a specific one is finished
    protected void filter(){
            Filter fil = new Filter();
            possibleSchedule = fil.filter(possibleSchedule);

    }

    //shrink each sequence to meet the maximum daily worker requirements; and add one with the  minimum score(finished in shortest days) to bestSchedule
    protected void shrink( ){

        for(int i = 0; i<possibleSchedule.size();i++){

            ArrayList<Job> al = possibleSchedule.get(i);
            //shrink the list
            Shrink shrin = new Shrink();
            Transfer result = shrin.shrink(al, max);
            int currentScore = result.returnScore();

            //adding the results to best schedule
            al = result.returnList();


            if(currentScore < bestScore){
//                System.out.println("intiitial chekc__________");
//                System.out.println(currentScore);
//                printList(al);

                bestScore = currentScore;
                bestSchedule.clear();
                bestSchedule.add(al);

            }
            else if (currentScore == bestScore){
//                System.out.println("danger!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                System.out.println(currentScore);
//                printList(al);
                bestSchedule.add(al);

           }

        }

    }

    // check the duplicate sequences in the bestSchedule array;
    protected void check(){

        for(int i = 0; i < bestSchedule.size(); i++){
            ArrayList cur = bestSchedule.get(i);

            // for the elements after cur, check whether there are ones that are the same as cur
            for(int p = i + 1; p < bestSchedule.size(); p++){
                ArrayList comp = bestSchedule.get(p);
                if(compare(cur, comp)){
                    bestSchedule.remove(p);
                    p--;}

            }
        }

    }

    // check whether two lists are the same
    public boolean compare(ArrayList<Job> list1, ArrayList<Job> list2){
        boolean same = true;
        outloop:
        for(Job job: list1){

            for(Job job2: list2){
                if(job.name == job2.name && job.getStartDate() != job2.getStartDate()){
                    same = false;
                    break outloop;
                }
            }
        }

        return same;
    }

    // print out the best solutions
    protected void print(){

        System.out.println("\nSUMMARY:");
        System.out.println("________________________________________________________\n");
        System.out.println("It requires at least " + bestScore + " days to finish this job \nThere are " + bestSchedule.size() + " ways to achieve that: \n");
        for(ArrayList<Job> ar: bestSchedule){
           printList(ar);
        }

        //printList(bestSchedule.get(0));

    }


    protected void printList(ArrayList<Job> arr){
        for(Job job: arr){
            System.out.println(job.name+" Start Date: " + job.getStartDate() );
        }
        System.out.println("________________________________________________________");
    }

}
