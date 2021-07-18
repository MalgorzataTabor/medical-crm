package pl.sda.medicalcrm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.medicalcrm.enums.TypeOfUser;
import pl.sda.medicalcrm.service.UserDetailsServiceImpl;

import javax.sql.DataSource;
import java.security.AuthProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /* private final DataSource dataSource;

     SecurityConfig(DataSource dataSource) {
         this.dataSource = dataSource;
     }*/
    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/patients").permitAll()
                .antMatchers("/doctors").permitAll()
                .antMatchers("/crmspecialists").permitAll()
                .antMatchers("/admins").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/clinics").permitAll()
                .antMatchers("/appointments").hasRole(TypeOfUser.PATIENT.name())
                .antMatchers("/admins/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/patients/**").hasRole("ADMIN")//.hasAnyRole()
                .antMatchers(HttpMethod.GET,"/patients/**").hasAnyRole("ADMIN","PATIENT")//.hasAnyRole()
                .antMatchers("/doctors/**").hasRole("DOCTOR")//.hasAnyRole()
                .antMatchers("/crmspecialists/**").hasRole("CRMSPECIALIST")//.hasAnyRole()
                //.antMatchers("/doctors/**").hasAnyRole("ADMIN", "DOCTOR")
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.jdbcAuthentication().dataSource(dataSource);

        auth.authenticationProvider(authenticationProvider);
    }
}






