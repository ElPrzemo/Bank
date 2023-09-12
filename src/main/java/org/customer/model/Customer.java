package org.customer.model;

import org.customer.menagment.CustomerManager;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class Customer {

    private static final CustomerManager manager = new CustomerManager();

    private String customerId; // Unikalny identyfikator klienta
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int age;
    private String email;
    private String phoneNumber;
    public CustomerLevel customerLevel;
    private Address address;
    private Gender gender; // Nowe pole "płeć"

    private Customer(CustomerBuilder builder) {
        this.customerId = UUID.randomUUID().toString(); // Generowanie unikalnego identyfikatora UUID
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthDate = builder.birthDate;
        this.age = builder.age;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.customerLevel = builder.customerLevel;
        this.address = builder.address;
        this.gender = builder.gender; // Inicjalizacja pola "płeć"
    }

    public static CustomerManager getManager() {
        return manager;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerLevel getCustomerLevel() {
        return customerLevel;
    }

    public Address getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender; // Getter dla pola "płeć"
    }

    public void printInfo() {
        manager.printCustomerInfo(this);
    }

    public static class CustomerBuilder {
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private int age;
        private String email;
        private String phoneNumber;
        private CustomerLevel customerLevel;
        private Address address;
        private Gender gender; // Nowe pole "płeć"

        public CustomerBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public CustomerBuilder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            calculateAge();
            return this;
        }

        private void calculateAge() {
            if (birthDate != null) {
                LocalDate currentDate = LocalDate.now();
                age = Period.between(birthDate, currentDate).getYears();
            }
        }

        public CustomerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CustomerBuilder customerLevel(CustomerLevel customerLevel) {
            this.customerLevel = customerLevel;
            return this;
        }

        public CustomerBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public CustomerBuilder gender(Gender gender) {
            this.gender = gender; // Ustawienie pola "płeć"
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}