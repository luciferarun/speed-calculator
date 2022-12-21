// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.util.*; 
import java.io.*;

class Main {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int inputs;
       System.out.println("Enter no inputs");
       inputs = sc.nextInt();
       double[] distance = new double[inputs];
       double[] time = new double[inputs];
       ShowResults s = new ShowResults();
       for(int i=0;i<inputs;i++){
           System.out.println("Enter distance "+i);
           distance[i] = sc.nextDouble();
           System.out.println("Enter time "+i);
           time[i] = sc.nextDouble();
       }
       ArrayList<DataSet> list = new ArrayList<DataSet>();
       list = s.setInput(distance,time);
       list = s.setSpeed(list);
       s.writeCSV(list);
    }
}
class DataSet{
    private double distance;
    private double time;
    private double speed;
    public DataSet(){
        
    }
    public DataSet(double distance,double time,double speed){
        this.distance = distance;
        this.time = time;
        this.speed = speed;
    }
    public void setSpeed(double speed){
        this.speed = speed;
    }
    public double getDistance(){
        return this.distance;
    }
    public double getTime(){
        return this.time;
    }
    public double getSpeed(){
        return this.speed;
    }
}
class UserInput extends DataSet {
    ArrayList<DataSet> listofuser = new ArrayList<DataSet>();
    public ArrayList<DataSet> setInput(double[] distance , double[] time)
    {
        for(int i=0;i<distance.length;i++){
            DataSet dataset = new DataSet(distance[i], time[i], 0);
            listofuser.add(dataset);
             //System.out.println(distance[i]+","+ time[i]);
         }
         return(listofuser);
    }
}
class Measure extends UserInput{
    public ArrayList<DataSet> setSpeed(ArrayList<DataSet> list){
        int n = list.size();
        for(int i=0;i<n;i++){
            DataSet dataset = list.get(i);
            double speed = dataset.getDistance()*dataset.getTime();
            list.get(i).setSpeed(speed);
        }
        return list;
    }
}
class  ShowResults extends Measure{
    public void writeCSV(ArrayList<DataSet> list){
         int n = list.size();
        System.out.println("Distance" + "   Time" + "     Speed");
       for(int i=0;i<n;i++){
            DataSet dataset = list.get(i);
            System.out.println(dataset.getDistance()+"       "+dataset.getTime()+"      "+dataset.getSpeed());
        }
        try {
            File file = new File("write.csv");
            PrintWriter write = new PrintWriter(file);
            for (DataSet dataset: list){
                write.println(dataset.toString());
            }
            System.out.println("Distance" + "   Time" + "     Speed");
            write.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}



