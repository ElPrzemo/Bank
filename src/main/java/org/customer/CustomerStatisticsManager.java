package org.customer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



    public class CustomerStatisticsManager {

        public static double calculateAverageAge(List<Customer> customers) {
            if (customers.isEmpty()) {
                return 0.0;
            }

            int totalAge = customers.stream()
                    .mapToInt(Customer::getAge)
                    .sum();

            return (double) totalAge / customers.size();
        }

        public static Map<Gender, Long> countCustomersByGender(List<Customer> customers) {
            return customers.stream()
                    .collect(Collectors.groupingBy(Customer::getGender, Collectors.counting()));
        }

        public static Map<Province, Long> countCustomersByProvince(List<Customer> customers) {
            return customers.stream()
                    .collect(Collectors.groupingBy(customer -> customer.getAddress().getProvince(), Collectors.counting()));
        }

        public static Map<CustomerLevel, Long> countCustomersByLevel(List<Customer> customers) {
            return customers.stream()
                    .collect(Collectors.groupingBy(Customer::getCustomerLevel, Collectors.counting()));
        }
    }

