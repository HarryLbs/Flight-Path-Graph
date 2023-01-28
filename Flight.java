public class Flight {
    protected String city;
    protected int cost; 
    protected int time;
    
    //For adding destinations
    Flight(String c, int co, int ti) {
        city = c;
        cost = co;
        time = ti;
    }
    
    public String getDest(){
        return city;
    }
    
    public int getCost() {
        return cost;
    }
    
    public int getTime() {
        return time;
    }
}