/*package andrew.cinema.CinemaRest.Config;


import andrew.cinema.CinemaRest.Entities.account;
import andrew.cinema.CinemaRest.Repositories.accountRepos;
import lombok.val;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        ;
    }
    @Bean
    public PrincipalExtractor principalExtractor(accountRepos userDetailsRepo) {
        return map -> {
            String id = (String) map.get("sub");

            account user = userDetailsRepo.findByIdaccount(id);
            if(user==null)
            {
                account newUser = new account();

                newUser.setIdaccount(id);
                newUser.setName((String) map.get("name"));
                newUser.setEmail((String) map.get("email"));
                newUser.setPicture((String) map.get("picture"));

                return newUser;
            }
            return userDetailsRepo.save(user);
        };
    }
}
*/


