package com.egomaa.customer.service;

import com.egomaa.customer.dto.CustomerDTO;
import com.egomaa.customer.dto.EmailAlreadyExistsException;
import com.egomaa.customer.entity.Customer;
import com.egomaa.customer.exception.CustomerNotFoundException;
import com.egomaa.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    private final ModelMapper mapper;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = customers.stream()
                .map(customer -> mapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
        return customerDTOList;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customerById = customerRepository.findById(id);
        if (customerById.isPresent()) {
            return mapper.map(customerById, CustomerDTO.class);
        }else {
            throw new CustomerNotFoundException("Customer not found with id : " + id);
        }
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = mapper.map(customerDTO, Customer.class);
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Invalid email used for another customer");
        }
        Customer savedCustomer = customerRepository.save(customer);
        return mapper.map(savedCustomer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());

        return mapper.map(customer,CustomerDTO.class);
    }

    @Override
    public void deleteCustomer(Long id) {
        CustomerDTO customerById = getCustomerById(id);
        customerRepository.deleteById(mapper.map(customerById,Customer.class).getId());
    }



}
