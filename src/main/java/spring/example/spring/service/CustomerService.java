package spring.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.example.spring.entity.Customer;
import spring.example.spring.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    public Customer getCustomerById(Long id) {
        return customerRepository.getReferenceById(id);
    }
}
