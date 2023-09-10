package org.customer;

public class Address {

    private String street;
    private String city;
    private String postalCode;
    private String country;
    private Province province;

    public Address(String street, String city, String postalCode, String country, Province province) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public Province getProvince() {
        return province;
    }
}



