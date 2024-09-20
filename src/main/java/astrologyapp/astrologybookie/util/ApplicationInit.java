package astrologyapp.astrologybookie.util;

import astrologyapp.astrologybookie.model.Role;
import astrologyapp.astrologybookie.model.User;
import astrologyapp.astrologybookie.model.enums.RoleName;
import astrologyapp.astrologybookie.repository.RoleRepository;
import astrologyapp.astrologybookie.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ApplicationInit implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ApplicationInit(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0){
            User user = new User();
            user.setEmail("admin@admin.bg");
            user.setPassword(passwordEncoder.encode("admin1234!"));
            Role role1 = new Role(RoleName.ADMIN);
            Role role2 = new Role(RoleName.USER);
            Set<Role> roles = Set.of(role1, role2);
            roleRepository.saveAll(roles);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
