package rs.novisad.crimetime.entity;

public class Cluster {
    private String name;
    private String[] addresses;
    private String keyword;

    public Cluster(String name, String[] addresses, String keyword) {
        this.name = name;
        this.addresses = addresses;
        this.keyword = keyword;
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
}
