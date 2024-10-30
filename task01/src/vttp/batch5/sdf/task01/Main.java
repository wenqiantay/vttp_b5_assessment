package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import vttp.batch5.sdf.task01.models.BikeEntry;

public class Main {

	public static void main(String[] args) {
		//read csv file
		File file = new File("day.csv");
		
		Map<String, Integer>

		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = br.readLine();
			//System.out.println(line);
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				BikeEntry.toBikeEntry(data);
			}
		} catch (Exception e) {
			System.err.println("Error reading file." + e.getMessage());
		}

		

	}
}
