package org.customer.menagment;

import org.customer.exceptions.CustomerNotFoundException;
import org.customer.model.Customer;
import org.customer.model.CustomerLevel;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers;

    public CustomerManager() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customer.getCustomerId(); // Wywołanie tej metody inicjuje identyfikator klienta
        customers.add(customer);
    }

    public void removeCustomer(String customerId) {
        customers.removeIf(customer -> customer.getCustomerId().equals(customerId));
    }

    public void updateCustomerInfo(String customerId, Customer updatedCustomerInfo) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId().equals(customerId)) {
                customers.set(i, updatedCustomerInfo);
                break;
            }
        }
    }

    public Customer getCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException(customerId); // Rzucenie wyjątku, jeśli klient nie zostanie znaleziony
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public void assignCustomerLevel(String customerId, CustomerLevel customerLevel) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                customer.setCustomerLevel(customerLevel);
                break;
            }
        }
    }

    public void printCustomerInfo(Customer customer) {
        System.out.println("Imię i nazwisko: " + customer.getFullName());
        System.out.println("Płeć: " + customer.getGender());
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