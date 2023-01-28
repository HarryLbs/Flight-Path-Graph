/******************************************************************************

Harrison Pounds
Graph Project

*******************************************************************************/
import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) {
	    Graph flightGraph = new Graph();
		try {
	        File myFile = new File("FlightData.txt");
	        Scanner myReader = new Scanner(myFile);
	        int totalLines = Integer.parseInt(myReader.nextLine());
	        while (totalLines > 0 ) {
	            String data = myReader.nextLine();
	            flightGraph.add(data);
	            totalLines--;
	        }
            myReader.close();
		} catch(Exception e) {
		    System.out.println("Flight Data file was not found.");
		}

		try {
	        File myFile = new File("RequestFlight.txt");
	        Scanner myReader = new Scanner(myFile);
	        int totalLines = Integer.parseInt(myReader.nextLine());
			int flightNum = 1;
	        while (totalLines >= flightNum ) {
                String data = myReader.nextLine();
                String[] temp = data.split("\\|");
                String city1 = temp[0];
                String city2 = temp[1];
                String type = temp[2];
				String typeS;
				if (type.equals("T"))
					typeS = "Time";
				else
					typeS = "Cost";
				System.out.printf("Flight %d: %s, %s (%s)\n", flightNum, city1, city2, typeS);
                flightGraph.dfs(city1, city2, type);
				System.out.println();
	            flightNum++;
	        }
            myReader.close();
		} catch(Exception e) {
		    System.out.println("Request Data file was not found.");
		}

	}

}
