package com.twilight.serviceImpls;

import com.twilight.objects.Customer;
import com.twilight.objects.User;
import com.twilight.repositories.UserRepository;
import com.twilight.services.CustomerService;

import com.twilight.types.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    final UserRepository userRepository;

    @Override
    public User create(String mobNo, String name) {
        User user = userRepository.findById(mobNo)
                .orElse(
                        User
                                .builder()
                                .mobNo(mobNo)
                                .blocked(false)
                                .build());
        user.getRoles().add(Role.customer);
        Customer customer = new Customer();
        customer.setName(name);
        customer.setMobNo(mobNo);
        customer.setUser(user);
        user.setCustomer(customer);
        userRepository.save(user);

        return null;
    }
}
