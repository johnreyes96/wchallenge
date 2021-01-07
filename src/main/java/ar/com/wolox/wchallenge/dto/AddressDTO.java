package ar.com.wolox.wchallenge.dto;

public class AddressDTO {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoDTO geoDTO;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public GeoDTO getGeo() {
        return geoDTO;
    }

    public void setGeo(GeoDTO geoDTO) {
        this.geoDTO = geoDTO;
    }
}