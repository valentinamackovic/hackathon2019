package rs.novisad.crimetime.entity;

public class Cluster {
    private String name;
    private String[] addresses;

    public Cluster(String name, String[] addresses) {
        this.name = name;
        this.addresses = addresses;
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
}
