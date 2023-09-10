package org.customer;

import java.time.LocalDate;
import java.time.Period;

public class Customer {


        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private int age;
        private String email;
        private String phoneNumber;
        private CustomerLevel customerLevel;
        private Address address;

        private Customer(CustomerBuilder builder) {
            this.firstName = builder.firstName;
            this.lastName = builder.lastName;
            this.birthDate = builder.birthDate;
            this.age = builder.age;
            this.email = builder.email;
            this.phoneNumber = builder.phoneNumber;
            this.customerLevel = builder.customerLevel;
            this.address = builder.address;
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

        public static class CustomerBuilder {
            private String firstName;
            private String lastName;
            private LocalDate birthDate;
            private int age;
            private String email;
            private String phoneNumber;
            private CustomerLevel customerLevel;
            private Address address;

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

            public Customer build() {
                return new Customer(this);
            }
        }
    }