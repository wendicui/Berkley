package Generator;
import java.util.*;

public class Shrink {

    public Shrink() {
    }

//  function that take an array of jobs and squah it so that the project finish as soon as possible; max is the number of workers allowed on site every day
    public Transfer shrink(ArrayList<Job> list, int max) {
        ArrayList<Job> finalLists = new ArrayList<Job>();

        //create an array to hold all the import dates: dates where job ends, cause that is where new worker spot will be availiabe and the new job should start the day after.
        ArrayList<Integer> pivotDate = new ArrayList<Integer>();

        // variable that holds the last date of this sequnce, namely the duration of the whole projects
        int score = 0;

        Job firstJob = list.get(0);
        //each time a new instance of Job should be created
        Job replicateFirstJob = new Job(firstJob);

        replicateFirstJob.setStartDate(1);
        Integer enddate = 1 + replicateFirstJob.getDuration() -1;

        //add the first job to final list and add the end date of first job to pivot date array;
        pivotDate.add(enddate);
        finalLists.add(replicateFirstJob);

        //loop through all the jobs to set each start date as early as possible.
        for (int i = 1; i < list.size(); i++) {

            Job originalTargetJob = list.get(i);

            //create new instance of jobs
            Job targetJob = new Job(originalTargetJob);
            Job jobBefore = finalLists.get(finalLists.size()-1);

            int workerNeeded = targetJob.getNumberOfWorker();

            // if it is the second element
            if (jobBefore.reqirement == "0") {
                // move current job to the days after the first job
                Integer currentStartDate = enddate + 1 ;
                Integer currentEndDate = currentStartDate + targetJob.getDuration()-1 ;

                targetJob.setStartDate(currentStartDate);
                pivotDate.add(currentEndDate);

                finalLists.add(targetJob);

            }
            else {
                int initialStartDate = jobBefore.getStartDate() + jobBefore.getDuration() -1 + 1;
                targetJob.setStartDate(initialStartDate);
                //loop through the pivot date array, find the earliest possible start date;
                //System.out.println(pivotDate);

                outloop:
                for (int j = 0; j < pivotDate.size(); j++) {
                    int totalWorker = 0;
                    Integer date = pivotDate.get(j);
//                    System.out.println("date is " + date);
//                    System.out.println(pivotDate);

                    //ensure that current job can not start earilier than previous job
                    if ((date + 1) >= jobBefore.getStartDate()) {

                        //loop through previous works, to see which ones will still be on going the next day;
                        for (int k = 0; k < i; k++) {
                            Job prevJob = finalLists.get(k);
                            if (date + 1 <= (prevJob.getStartDate() + prevJob.getDuration() -1)) {
                                //this job is not finished yet, worker for this job should be added;
                                totalWorker = totalWorker + prevJob.getNumberOfWorker();
                            }
                        }


                       if (workerNeeded + totalWorker <= max) {

                            //meet daily maximum worer environment, check whether meet job sequence requirement
                            if (targetJob.reqirement != null) {
                                String req = targetJob.reqirement;
                                // find the requirement job;
                                innerloop:
                                for (int h = 0; h < i; h++) {
                                    Job reqJob = finalLists.get(h);
                                    if (reqJob.name == req) {

                                        // if the requirement job is finished
                                        if (reqJob.getStartDate() + reqJob.getDuration()-1 <= date) {
                                           // System.out.println("bingo");
                                            targetJob.setStartDate(date + 1);
                                            break outloop;

                                        } else {
                                           // System.out.println("here");
                                           break innerloop;
                                        }
                                    }
                                }
                            } else {

                                targetJob.setStartDate(date +1);
                                break outloop;
                            }
                        }
                    }
                }

                //check whether the job can be moved to day 1;day 1 is not in pivotDate, but job could also start there
                if(jobBefore.getStartDate() == 1){
                    int workers = 0;
                    //loop through previous job to see which start on day 1
                    for(int m = 0; m < i; m ++){
                        Job prevWork = finalLists.get(m);
                        if(prevWork.getStartDate() == 1){
                            workers = workers + prevWork.getNumberOfWorker();
                        }
                    }

                    if((workers + targetJob.getNumberOfWorker()) <= max){
                        //check for requirements, only one without requirments can start on day 1
                        if(targetJob.reqirement == null)
                        targetJob.setStartDate(1);
                      //  System.out.println("wuha");
                    }
                }

                // add the current job end date to pivotDate array and sort it;
                Integer targetEndDate = targetJob.getStartDate() + targetJob.getDuration() - 1;
                pivotDate.add(targetEndDate);
                Collections.sort(pivotDate);


                finalLists.add(targetJob);
            }

        }
        score = Collections.max(pivotDate);
        // create a Transfer object to pass on both the finalLists and score
        Transfer trans = new Transfer(score, finalLists);

        return trans;
    }

    public static void main(String[] args) {
        //get the scheduler
        Scheduler gener = new Scheduler(6);

        //Job demolition = new Job("demolition", 0, 3, 4,"0");
        Job wallFrame = new Job("wallframe", 0, 5, 1);
        Job mechanical = new Job("mechanical", 0, 4, 1);
        Job wallFinish = new Job("wallfinish", 0, 1, 2);
        Job ceiling = new Job("ceiling", 0, 1, 2,"wallfinish");

       // gener.addJob(demolition);
        gener.addJob(wallFrame);
        gener.addJob(mechanical);
        gener.addJob(wallFinish);
        gener.addJob(ceiling);

        gener.shuffle();

        Filter fil = new Filter();
        ArrayList<ArrayList<Job>> result = fil.filter(gener.possibleSchedule);

        Shrink shr = new Shrink();
        System.out.println("size is " + result.size());


        for(ArrayList<Job> test: result){
            Transfer tran = shr.shrink(test, 6);

            ArrayList<Job>  fin =  tran.returnList();

            for(Job job: fin){
                System.out.println(job.name + ": " + job.getStartDate());
            }

            System.out.println("The socre is " + tran.returnScore());
            System.out.println("______________________________" );
        }
    }
}