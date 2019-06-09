package rs.novisad.crimetime.entity;

public class Cluster {
    private String name;
    private String[] addresses;
    private String keyword;
    private int numberOfAccidents;
    private int riskPoints;
    private double lon;
    private double lat;

    public Cluster(String name, String[] addresses, String keyword,double lon,double lat) {
        this.name = name;
        this.addresses = addresses;
        this.keyword = keyword;
        this.lon=lon;
        this.lat=lat;
    }
    
    public Cluster(String name, String[] addresses, String keyword) {
        this.name = name;
        this.addresses = addresses;
        this.keyword = keyword;
    }

    public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAddresses() {
        return addresses;
    }

    public  void setAddresses(String[] addresses) {
        this.addresses = addresses;
    }


    public String getKeyword() {
        return keyword;
    }

	public int getNumberOfAccidents() {
		return numberOfAccidents;
	}

	public void setNumberOfAccidents(int numberOfAccidents) {
		this.numberOfAccidents = numberOfAccidents;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getRiskPoints() {
		return riskPoints;
	}

	public void setRiskPoints(int riskPoints) {
		this.riskPoints = riskPoints;
	}
	
	
    
    
}
