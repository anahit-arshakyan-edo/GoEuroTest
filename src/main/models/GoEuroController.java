package main.models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.json.JSONArray;

public class GoEuroController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GoEuroModel goEuroModel = new GoEuroModel();
		FileInputUpdateService fileInputUpdateService = new FileInputUpdateService();
		String filePath = "dataBase.csv";
		System.out.println("For Exit please enter 0 or Exit!");
		System.out.println("Input city name: ");
		while(true){
			try {
				
				BufferedReader bufferRead = new BufferedReader(
						new InputStreamReader(System.in));
				String inputString = bufferRead.readLine();
				if(inputString.equals("0") || inputString.equalsIgnoreCase("Exit")){
					System.out.println("Bye!!!!!");
					return;
				}else if(!inputString.equals("")){
					String jsonString = readURLData("http://api.goeuro.com/api/v2/position/suggest/en/"	+ inputString);
					JSONArray jsonArray = new JSONArray(jsonString);
					
					for (int i=0; i<jsonArray.length(); i++) {
						goEuroModel.setId(jsonArray.getJSONObject(i).getInt("_id"));
						goEuroModel.setName(jsonArray.getJSONObject(i).getString("name").replace(",", " "));
						goEuroModel.setType(jsonArray.getJSONObject(i).getString("type"));
						goEuroModel.setLatitude(jsonArray.getJSONObject(i).getJSONObject("geo_position").getDouble("latitude"));
						goEuroModel.setLongitude(jsonArray.getJSONObject(i).getJSONObject("geo_position").getDouble("longitude"));
						fileInputUpdateService.createUpdateFile(filePath, goEuroModel);
					}	
				}
		
			} catch (Exception e) {
				System.out.println("Error in CsvFileWriter !!!");
				//e.printStackTrace();
			}
		}
	}

	public static String readURLData(String myURL) {
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + myURL,
					e);
		}

		return sb.toString();
	}
}
