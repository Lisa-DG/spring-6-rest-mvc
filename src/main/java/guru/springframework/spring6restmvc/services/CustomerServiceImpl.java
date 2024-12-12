package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, Customer> customerMap = new HashMap<>();

    public CustomerServiceImpl() {
        Customer cust1 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("John")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Customer cust2 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("Lisa")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Customer cust3 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("Suzy")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customerMap.put(cust1.getId(), cust1);
        customerMap.put(cust2.getId(), cust2);
        customerMap.put(cust3.getId(), cust3);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        log.debug("Get Customer by Id - in service. Id: {}", id.toString());

        return customerMap.get(id);
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {
        Customer savedCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .name(customer.getName())
                .version(customer.getVersion())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }

    @Override
    public void updateCustomerById(UUID id, Customer customer) {
        Customer existing = customerMap.get(id);
        existing.setName(customer.getName());

        customerMap.put(existing.getId(), existing);
    }

    @Override
    public void deleteCustomerById(UUID id) {
        customerMap.remove(id);
    }

    @Override
    public void patchCustomerById(UUID id, Customer customer) {
        Customer existing = customerMap.get(id);

        if (StringUtils.hasText(customer.getName())) {
            existing.setName(customer.getName());
        }
    }

}
