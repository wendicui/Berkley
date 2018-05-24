package Generator;
import java.util.*;

public class Filter {
    public Filter() {
    }

    // filter sequences that do not meet job sequence requirments: namely one job can not start until the required job is finished
    public ArrayList<ArrayList<Job>> filter(ArrayList<ArrayList<Job>> list) {
        ArrayList<ArrayList<Job>> finalLists = new ArrayList<ArrayList<Job>>();

        for(ArrayList<Job> ar: list){
            int size = ar.size();
            boolean qualify = true;

            outloop:
            for(int i = 0; i < size; i ++){
                Job jb = ar.get(i);
                String req = jb.reqirement;

                if(req != null){
                    //check if there is work that should start before all
                    if(req == "0" && i!= 0){
                        qualify = false;
                        break outloop;
                    }else {
                     //check if the sequence meets requirements of all jobs
                        for (int j = i; j < size; j++) {
                            Job after = ar.get(j);
                            if (req == after.name) {
                                qualify = false;
                                break outloop;
                            }
                        }
                    }
                }
            }

            if(qualify) finalLists.add(ar);
        }

        return finalLists;
    }



    public static void main(String[] args) {
        //get the scheduler
        Scheduler gener = new Scheduler(6);

        //get the job inputs
        //Job demolition = new Job("demolition",0, 5,5,"0");
        Job wallFrame = new Job("wallframe",0,3,2);
        Job mechanical = new Job("mechanical",0,6,3,"wallframe");
        Job ceiling = new Job("ceiling",0,1,2);


        //gener.addJob(demolition);
        gener.addJob(wallFrame);
        gener.addJob(mechanical);
        gener.addJob(ceiling);

        gener.shuffle();

        Filter fil = new Filter();
        ArrayList<ArrayList<Job>> result = fil.filter(gener.possibleSchedule);

        System.out.println(result);

       // System.out.println("____________________");

       // System.out.println(gener.possibleSchedule.size());

       // gener.setScheduleLimit();




    }
}
