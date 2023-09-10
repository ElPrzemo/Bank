package org.customer;

public class CustomerManager {

    public void printCustomerInfo(Customer customer) {
        System.out.println("Imię i nazwisko: " + customer.getFullName());
        System.out.println("Data urodzenia: " + customer.getBirthDate());
        System.out.println("Wiek: " + customer.getAge());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Numer telefonu: " + customer.getPhoneNumber());
        System.out.println("Poziom klienta: " + customer.getCustomerLevel());
        System.out.println("Adres: " + customer.getAddress().getStreet()
                + ", " + customer.getAddress().getCity() + ", "
                + customer.getAddress().getPostalCode()
                + ", " + customer.getAddress().getCountry() + ", "
                + customer.getAddress().getProvince());
    }
}
