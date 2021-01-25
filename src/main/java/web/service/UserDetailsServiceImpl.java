package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDAO.getUserByName(username);
        return user;
    }
}
//private Collection<? extends GrantedAuthority> getAuthorities(
//  Collection<Role> roles) {
//    List<GrantedAuthority> authorities
//      = new ArrayList<>();
//    for (Role role: roles) {
//        authorities.add(new SimpleGrantedAuthority(role.getName()));
//        role.getPrivileges().stream()
//         .map(p -> new SimpleGrantedAuthority(p.getName()))
//         .forEach(authorities::add);
//    }
//
//    return authorities;
//}

