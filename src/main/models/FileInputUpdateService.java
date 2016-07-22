package main.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class FileInputUpdateService {
	//Delimiter used in CSV file
	//private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private static final String FILE_HEADER = "id,City Name,Type,Latitude,Longitude";
	private FileWriter fileWriter = null;
	public FileInputUpdateService() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isFileExists(String filePath) {
		File newFile = new File(filePath);
		return newFile.exists();
	}
	
	public void createUpdateFile(String filePath, GoEuroModel goEuroModel) {
		try{
			
			if(isFileExists(filePath)){
				fileWriter = new FileWriter(filePath, true);
			}else{
				fileWriter = new FileWriter(filePath);
				//Write the CSV file header
				fileWriter.append(FILE_HEADER.toString());
				//Add a new line separator after the header
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
									
			//Write a new city data row to the CSV file
				/*fileWriter.append(String.valueOf(goEuroModel.getId()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(goEuroModel.getName());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(goEuroModel.getLatitude()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(goEuroModel.getLongitude()));
				fileWriter.append(NEW_LINE_SEPARATOR);*/
			
			fileWriter.append(goEuroModel.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
									
			System.out.println("Added new Data: "+goEuroModel.toString());
			
		}catch (FileNotFoundException e) {
			System.out.println("There is not such file or cannot access the file because it is being used by another process !!!");
         //   e.printStackTrace();
		}  catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			//e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error while flushing/closing fileWriter !!!");
				//e.printStackTrace();
			}
			
		}

	}

}
