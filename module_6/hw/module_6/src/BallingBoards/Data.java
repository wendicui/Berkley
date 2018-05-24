package BallingBoards;

import  java.util.*;

public class Data {
    private HashMap<String, ArrayList> data = new HashMap<String, ArrayList>();

    //constructor
    public Data(){}

    //take input
    protected void input(String name, int score, Date matchDate ){
        ArrayList<ArrayList> value = data.get(name);
        //create the arraylist that hold score and matchDate
        ArrayList newGame = new ArrayList();
        newGame.add(score);
        newGame.add(matchDate);

        //check if player exist
        if(value != null){
            value.add(newGame);
        }else{
            ArrayList<ArrayList> games = new ArrayList<ArrayList>();
            games.add(newGame);
            data.put(name,games);
        }
    }

    //print all the data
    protected void printData (){
        for(String player :data.keySet()){
            ArrayList<ArrayList>  playerData = data.get(player);
            System.out.println("Player "+ player + ": \n");
            printPlayer(playerData);
        }
    }

    //print single player data
    protected void printPlayer(ArrayList<ArrayList>  list){
        int scores, latestScore ;
        scores = latestScore = 0;
        Date oringinDate = new GregorianCalendar(0000, Calendar.JANUARY, 01).getTime();

        for(ArrayList item: list){
           Object gameScore = item.get(0);
           Date gameDate = (Date)item.get(1);

           scores += (Integer)gameScore;
           //check for latest game
           if(gameDate.after(oringinDate)){
                oringinDate = gameDate;
                latestScore = (Integer) gameScore;
            }

        }

        float averageScore = (float)scores/list.size();

        System.out.println("Number of Games: " + list.size()+ "\n");
        System.out.println("Average Score of Games: " + averageScore + "\n");
        System.out.println("Score of last game: " + latestScore + "\n");
        System.out.println(("Date of last game: "+ oringinDate));
        System.out.println("\n_______________________________________\n");
    }


}
