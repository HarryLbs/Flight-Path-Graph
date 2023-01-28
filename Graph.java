import java.util.ArrayList;

public class Graph {

    LinkedList<City> adjList;

    public Graph() {
        adjList = new LinkedList<City>();
    }

    public void add(String data) {
        String[] temp = data.split("\\|");
        String city1 = temp[0];
        String city2 = temp[1];
        int cost = Integer.parseInt(temp[2]);
        int time = Integer.parseInt(temp[3]);
        Flight newFlight = new Flight(city2, cost, time);

        if (findCity(city1) == null) {
            City newCity = new City(city1);
            newCity.getFlights().add(newFlight);
            adjList.add(newCity);
        }else {
            findCity(city1).getValue().getFlights().add(newFlight);;
        }

        newFlight = new Flight(city1, cost, time);

        if (findCity(city2) == null) {
            City newCity = new City(city2);
            newCity.getFlights().add(newFlight);
            adjList.add(newCity);
        }else {
            findCity(city2).getValue().getFlights().add(newFlight);;
        }
    }

    public  Node<City> findCity(String cityName) {
        Node<City> current = adjList.getTop();
        while (current != null) {
            String currentCity = current.getValue().getCity();
            if (currentCity.equals(cityName)){
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public String dfs(String c1, String c2, String type) {
        LinkedList<City> visited = new LinkedList<>();
        Node<City> currentCity = findCity(c2);
        ArrayList<LinkedList<City>> workingPaths = new ArrayList<>();
        if(findCity(c2) == null || findCity(c1) == null) {
            System.out.println("None.");
            return null;
        }
        visited.push(currentCity.getValue());
        while (!visited.isEmpty()){
            Node<Flight> currentFlight = currentCity.getValue().getFlights().getTop();
            if (currentCity.getValue().cityName.equals(c1)){ //Found destination
                currentFlight = null;
                
                workingPaths.add(new LinkedList<City>(visited.head));
            }else{
                while (currentFlight != null) {
                    if( !containsFlight(visited, currentFlight.getValue())) { //add flight
                        currentCity = findCity(currentFlight.getValue().getDest());
                        visited.push(currentCity.getValue());
                        break;
                    }
                    currentFlight = currentFlight.next;
                }
            }
            while(currentFlight == null ) {
                City tempC = visited.pop();
                if(visited.isEmpty()){
                    break;
                }
                currentCity = visited.getTop();
                currentFlight = currentCity.getValue().getFlights().getTop();
                while (!currentFlight.getValue().getDest().equals(tempC.getCity())){
                    currentFlight = currentFlight.next;
                }
                if (currentFlight != null)
                    currentFlight = currentFlight.next;
                while (currentFlight != null) {
                    if( !containsFlight(visited, currentFlight.getValue())) { //add flight
                        currentCity = findCity(currentFlight.getValue().getDest());
                        visited.push(currentCity.getValue());
                        break;
                    }
                    currentFlight = currentFlight.next;
                }
            }
        }
        ArrayList<LinkedList<City>> newshit = workingPaths;
        printPaths(findBestPaths( newshit, type));
        return null;
    }

    public ArrayList<LinkedList<City>> findBestPaths(ArrayList<LinkedList<City>> paths, String type) {
        if (paths.size() <= 3) {
            return paths;
        }
        ArrayList<LinkedList<City>> returnPaths = new ArrayList<>();
        
        for (int k = 0; k < 3; k++) {
            int minIndex = 0;
            for (int i = 0; i < paths.size(); i++) {
                System.out.println(paths.size());
                LinkedList<City> path = paths.get(i);
                System.out.println("Testing 123 "+path.head.getValue().getCity());
                int cost = Integer.MAX_VALUE;
                int time = Integer.MAX_VALUE;
                int currentCost = 0;
                int currentTime = 0;
                while (path != null){
                    String temp = path.pop().getCity();
                    if(path.isEmpty())
                        break;
                    Node<Flight> pathCurrent =  path.getTop().getValue().getFlights().getTop();
                    while(!pathCurrent.getValue().getDest().equals(temp)) {
                        pathCurrent = pathCurrent.next;
                    }
                    cost += pathCurrent.value.getCost();
                    time += pathCurrent.value.getTime();
                }
                if (type.equals("C")){
                    if (currentCost < cost) {
                        cost = currentCost;
                        minIndex = i;
                    }
                }else {
                    if (currentTime < time) {
                        time = currentTime;
                        minIndex = i;
                    }
                }

            }
            System.out.println(minIndex);
            returnPaths.add(new LinkedList<City>(paths.get(minIndex).head));
            paths.remove(minIndex);
        }
        return returnPaths;
    }

    public void printPaths(ArrayList<LinkedList<City>> paths) {
        for (int i = 0; i < paths.size(); i++) {
            String resultPrint = "Path "+(i+1)+": ";
            LinkedList<City> path = paths.get(i);
            int cost = 0;
            int time = 0;
            while (path != null){
                String temp = path.pop().cityName;
                resultPrint += temp;
                if(path.isEmpty()){
                    break;
                }

                Node<Flight> pathCurrent =  path.getTop().getValue().getFlights().getTop();
                while(!pathCurrent.getValue().getDest().equals(temp)) {
                    pathCurrent = pathCurrent.next;
                }
                cost += pathCurrent.value.getCost();
                time += pathCurrent.value.getTime();
                resultPrint += " -> ";
            }
            resultPrint += ". Time: "+time + " Cost: "+cost;
            System.out.println(resultPrint);
        }
    }


    public boolean containsFlight(LinkedList<City> path, Flight flight) {
        Node<City> pathCurrent =  path.getTop();
        while (pathCurrent != null) {
            if (pathCurrent.getValue().getCity().equals(flight.getDest())) {
                return true;
            }
            pathCurrent = pathCurrent.next;
        }
        return false;
    }
}