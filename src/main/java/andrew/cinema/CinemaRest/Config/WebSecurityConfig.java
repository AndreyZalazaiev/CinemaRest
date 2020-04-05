/*package andrew.cinema.CinemaRest.Config;

import andrew.cinema.CinemaRest.Entities.account;
import andrew.cinema.CinemaRest.Entities.user;
import andrew.cinema.CinemaRest.Repositories.accountRepos;
import andrew.cinema.CinemaRest.Repositories.userRepos;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.preauth.x509.X509PrincipalExtractor;

import java.security.Principal;
import java.time.LocalDateTime;

@Configuration
@EnableOAuth2Sso
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

    }
@Bean
    public PrincipalExtractor principalExtractor(userRepos userRep){
        return map -> {
            String id= (String)map.get("sub");
            user registeredUser =userRep.findById(id).orElseGet(() ->{
                user newUser = new user();
                newUser.setId(id);
                newUser.setName((String)map.get("name"));
                newUser.setEmail((String)map.get("email"));
                newUser.setUserpic((String)map.get("picture"));
                newUser.setLocale((String)map.get("locale"));
                newUser.setGender((String)map.get("gender"));

                return newUser;
            } );
            registeredUser.setLastVisit(LocalDateTime.now());
            userRep.save(registeredUser);
            return registeredUser;
        };

}

}


*/
