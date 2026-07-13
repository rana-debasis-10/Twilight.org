package com.twilight.serviceImpls;

import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.objects.Customer;
import com.twilight.objects.Location;
import com.twilight.repositories.CustomerAddressRepository;
import com.twilight.repositories.CustomerRepository;
import com.twilight.services.CustomerService;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerAddressRepository customerAddressRepository;



    @Override
    public Customer load(String mobNo) throws UnAuthorizedException {
        return customerRepository.findById(mobNo).orElseThrow(()->new NotFoundException("User trying to load without registration","Not registered"));
    }

    @Override
    public void create(String mobNo, String name) throws UnAuthorizedException{
        customerRepository.findById(mobNo)
                .ifPresent(customer ->{
                        throw new UnAuthorizedException("User trying to register again","User exists");
                    }
                );
        Customer customer = new Customer();
        customer.setName(name);
        customer.setMobNo(mobNo);
        customerRepository.save(customer);
    }

    @Override
    public void addAddressAsType(@NonNull Customer customer, @NotNull Location address) {
        address.setCustomer(customer);
        customerAddressRepository.save(address);
    }


}
