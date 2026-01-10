package com.twilight.eCommercePlatform.services;

import com.twilight.eCommercePlatform.dto.entity.UserDTO;
import com.twilight.eCommercePlatform.entities.User;
import com.twilight.eCommercePlatform.enums.UserRole;
import com.twilight.eCommercePlatform.mapper.UserMapper;
import com.twilight.eCommercePlatform.repositories.RoleRepo;
import com.twilight.eCommercePlatform.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public List<User> getAllUser(int pageNum) {
        return userRepo.findAll(PageRequest.of(pageNum,10)).getContent();
    }
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }
    public User saveAsUser(UserDTO userDTO){
        User user = userMapper.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
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
    public HttpStatus updateRole(String email,UserRole role){
        User user = getUserByEmail(email);
        if(user!= null ){
        user.setRole(roleRepo.findByUserRole(role));
        return HttpStatus.OK;
        }
        else
            return HttpStatus.NOT_FOUND;

    }
    public HttpStatus makeAdmin (String email){
        return updateRole(email,UserRole.ADMIN);
    }
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
