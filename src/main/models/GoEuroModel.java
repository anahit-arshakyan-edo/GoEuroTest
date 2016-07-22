package main.models;

public class GoEuroModel {
	private int id;
	private String name;
	private String type;
	private double latitude;
	private double longitude;

	
	
	public GoEuroModel() {
		
	}
	
	public GoEuroModel(int id, String name, String type, double longitude, double latitude) {			
		this.id = id;
		this.name=name;
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString(){
		String objectString = "";
		objectString = this.id+"," +this.name+"," +this.type+","+this.latitude+"," +this.longitude;		
		return objectString;
		
	}
}
