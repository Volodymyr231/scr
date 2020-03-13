package pro.edu.scr.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static pro.edu.scr.security.ApplicationUserRole.ADMIN_ROLE;
import static pro.edu.scr.security.ApplicationUserRole.DOCTOR_ROLE;

@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests().
               antMatchers("/", "/css", "/js").
               permitAll()
               .antMatchers("/api/person/get**").hasRole(DOCTOR_ROLE.name())
               .antMatchers("/api/**").hasRole(ADMIN_ROLE.name())
               .anyRequest().
               authenticated().
               and().
               httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User
                .builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .roles()
                .build();


         UserDetails admin = User
                 .builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(ADMIN_ROLE.name())
                .build();

         UserDetails doctor = User
                 .builder()
                .username("doctor")
                .password(passwordEncoder.encode("doctor"))
                .roles(DOCTOR_ROLE.name())
                .build();

        return  new InMemoryUserDetailsManager(user,admin,doctor);
    }
}
