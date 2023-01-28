public class City {
    protected String cityName;
    protected LinkedList<Flight> flightList;
    
    //cost and time will be 0 for first node as it represents the city.
    City(String c) { 
        cityName = c;
        flightList = new LinkedList<Flight>();
    }

    public LinkedList<Flight> getFlights() {
        return flightList;
    }

    public String getCity() {
        return cityName;
    }
}