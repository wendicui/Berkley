package Generator;

//each scheduler takes a number to limit the number of personnel that could be on site every day

// each job may have requirement as which job should be finished before this one can start

// "0" in requirements means that this job should be finished before all others can take place;

// one requirement of this program is to start a job as early as possible, to leave contingency in case of unexpected disturbance;

public class test {

   public test(){};


    public static void main(String[] args) {
        //get the scheduler
        Scheduler gener = new Scheduler(6);

        //get the job inputs, this input tested:
        //"0" requirments;
        // job sequence requirements;
        // maximum personell requirements;

        Job demolition = new Job("demolition", 0, 3, 4,"0");
        Job wallFrame = new Job("wallframe", 0, 5, 1);
        Job mechanical = new Job("mechanical", 0, 4, 5);
        Job wallFinish = new Job("wallfinish", 0, 1, 2);
        Job ceiling = new Job("ceiling", 0, 3, 2,"wallframe");



        gener.addJob(demolition);
        gener.addJob(wallFrame);
        gener.addJob(mechanical);
        gener.addJob(wallFinish);
        gener.addJob(ceiling);


        gener.start();

    }

}
