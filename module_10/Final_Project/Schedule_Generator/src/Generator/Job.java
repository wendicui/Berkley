package Generator;

public class Job {

    //set variables that represent the starte date, duration and number of workers required;
    private int startDate, duration, numberOfWorker;
    //name represents the name of the job; the content of requirements represents the work that must be FINISHED
    // (or come before) before this work;
    //"0" in requirement means the work must come before all works, and should be finished before all works.
    protected String reqirement, name;


    public Job(String name, int start, int duration, int numberOfWorker, String req){
        this.startDate = start;
        this.duration = duration;
        this.numberOfWorker = numberOfWorker;
        this.reqirement = req;
        this.name = name;
    }

    public Job(String name,int start, int duration, int numberOfWorker){
        this.startDate = start;
        this.duration = duration;
        this.numberOfWorker = numberOfWorker;
        this.name = name;
    }
    //for create new instance of the same job info
    public Job(Job inputJob){
        this.startDate = inputJob.getStartDate();
        this.duration = inputJob.getDuration();
        this.numberOfWorker = inputJob.numberOfWorker;
        this.reqirement = inputJob.reqirement;
        this.name = inputJob.name;
    }


    //functions to retreive variables

    protected int getStartDate(){
        return startDate;
    }

    protected int getDuration(){
        return duration;
    }

    protected int getNumberOfWorker(){
        return numberOfWorker;
    }

    //function to set variable values
    protected void setStartDate(int newDate){
        this.startDate = newDate;
    }


}
