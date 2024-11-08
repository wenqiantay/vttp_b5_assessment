package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import vttp.batch5.sdf.task01.models.BikeEntry;

public class Main {

	public static void main(String[] args) {
		//read csv file
		
		try{
			File file = new File("day.csv");
			BufferedReader br = new BufferedReader(new FileReader(file));
			List<BikeEntry> entry = new ArrayList<>();
			
			//Ignore first line - headers
			String line = br.readLine();
			while(line != null) {
				line = br.readLine();
				if(line == null)
					break;
				String[] data = line.split(",");
				
				//Store data into a List
				BikeEntry newEntry = BikeEntry.toBikeEntry(data);
				entry.add(newEntry);	
			}
			br.close();
			
			//Sort the data (add the sum of casual and registered cyclist)
			List<BikeEntry> sortedData = entry.stream()
										.sorted((v1, v2) -> Integer.compare(v1.getCasual() + v1.getRegistered(), v2.getCasual() + v2.getRegistered()))
										.collect(Collectors.toList());
			//Sort from lowest number to highest number
			Collections.reverse(sortedData);

			List<BikeEntry> topFiveDaysData = sortedData.subList(0, 5);
			String[] position = {"highest", "second highest", "third highest", "fourth highest", "fifth highest"};
			String[] weatherWords = {"Clear, Few clouds, Partly cloudy, Partly cloudy", 
								"Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist",
								"Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds",
								"Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog"}; 
			//print out the data
			for(int i = 0; i < topFiveDaysData.size(); i++) {
				BikeEntry bikeEntry = topFiveDaysData.get(i);
				String pos = position[i];
				String season = Utilities.toSeason(bikeEntry.getSeason());
				String day = Utilities.toWeekday(bikeEntry.getWeekday());
				String month = Utilities.toMonth(bikeEntry.getMonth());
				int total = bikeEntry.getCasual() + bikeEntry.getRegistered();
				String weather = weatherWords[bikeEntry.getWeather()- 1];
				String holiday = (bikeEntry.isHoliday()) ?  "a holiday" : "not a holiday";

				System.out.printf("The %s (position) recorded number of cyclists was in %s (season), on a  %s (day) in the month of  %s (month). There were a total of %d (total) cyclists.The weather was %s (weather). %s (day) was %s.\n", 
									pos, season, day, month, total, weather, day, holiday);
			

			}

			

		} catch (Exception e) {
			System.err.println("Error reading file." + e.getMessage());
		}

		

	}
}
