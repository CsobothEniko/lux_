package hu.dreamteam.lux.service;

import hu.dreamteam.lux.entity.Role;
import hu.dreamteam.lux.entity.User;
import hu.dreamteam.lux.repo.RoleRepo;
import hu.dreamteam.lux.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private static final String USER_ROLE = "user";

    @Autowired
    private void setRepo(UserRepo userRepo, RoleRepo roleRepo){
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByEmail(s);
        if(user == null){
            throw new UsernameNotFoundException(s);
        }
        return new MyUserPrincipal(user);
    }

    public User findByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public void registerUser(User user){
        Role userRole = roleRepo.findByRole(USER_ROLE);
        if(userRole == null) {
            userRole = roleRepo.save(new Role(USER_ROLE));
        }
        user.getRoles().add(userRole);
        User u = userRepo.save(user);
    }

}
