package com.terminbuchung.backend.customer;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = toEntity(customerDTO);
        Customer savedCustomer = customerService.save(customer);
        return toDTO(savedCustomer);
    }

    // Mapping-Methoden: Entity -> DTO
    private CustomerDTO toDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setVorname(customer.getVorname());
        dto.setNachname(customer.getNachname());
        dto.setEmail(customer.getEmail());
        return dto;
    }

    // Mapping-Methoden: DTO -> Entity
    private Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setVorname(dto.getVorname());
        customer.setNachname(dto.getNachname());
        customer.setEmail(dto.getEmail());
        return customer;
    }
}