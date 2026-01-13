package com.twilight.eCommercePlatform.services;

import com.twilight.eCommercePlatform.dto.entity.UserDTO;
import com.twilight.eCommercePlatform.entities.Role;
import com.twilight.eCommercePlatform.entities.User;
import com.twilight.eCommercePlatform.enums.UserRole;
import com.twilight.eCommercePlatform.mapper.UserMapper;
import com.twilight.eCommercePlatform.repositories.RoleRepo;
import com.twilight.eCommercePlatform.repositories.UserRepo;
import com.twilight.eCommercePlatform.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RoleRepo roleRepo;

    public List<UserDTO> getAllUser(int pageNum) {
        List<UserDTO> list=new ArrayList<>();
        userRepo.findAll(PageRequest.of(pageNum,10)).getContent().forEach(user -> {list.add(userMapper.toDTO(user));});
        return list;
    }
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }
    @Transactional
    public UserDetailsImpl saveAsUser(UserDTO userDTO){
        User user = userMapper.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return new UserDetailsImpl(user,roleRepo.findByUserRole(user.getRole()).getAuthorities());
    }
    public void saveUser(User user){
        userRepo.save(user);
    }
    public HttpStatus updatePassword(String newPassword , String email){
        User user = getUserByEmail(email);
        if (user!= null){
           user.setPassword(passwordEncoder.encode(newPassword));
           return HttpStatus.OK;
        }
        else{
            return HttpStatus.NOT_FOUND;
        }

    }
    private HttpStatus updateRole(String email,UserRole role){
        User user = getUserByEmail(email);
        if(user!= null ){
        user.setRole(role);
        return HttpStatus.OK;
        }
        else
            return HttpStatus.NOT_FOUND;

    }
    public HttpStatus makeAdmin (String email){
        return updateRole(email,UserRole.ADMIN);
    }
    public HttpStatus makeRestaurantOwner(String email){
        return updateRole(email,UserRole.RESTAURANT_OWNER);}
    public HttpStatus disableAccount(String email){
        User user = getUserByEmail(email);
        if (user!= null){
            user.setEnabled(false);
            return HttpStatus.OK;
        }
        else
            return HttpStatus.NOT_FOUND;
    }


}
