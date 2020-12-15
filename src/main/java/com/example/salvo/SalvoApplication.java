package com.example.salvo;

import com.example.salvo.model.*;
import com.example.salvo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.example.salvo.model.*;
import com.example.salvo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);

	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/* @Bean
     public CommandLineRunner initData(PlayerRepository playerRepository,
                                       GameRepository gameRepository,
                                       GamePlayerRepository gamePlayerRepository,
                                       ShipRepository shipRepository,
                                     SalvoRepository salvoRepository,
                                       ScoreRepository scoreRepository) {
         /*return (args) -> {

             //creamos y guardamos a los jugadores en el repositorio
             Player player1 = new Player("david@gmail.com","David", passwordEncoder().encode("123"));
             Player player2 = new Player("rocket@gmail.com" , "Rocket", passwordEncoder().encode("123"));
             Player player3 = new Player("lilian@gmail.com" , "Liliana",passwordEncoder().encode("123") );
             Player player4 = new Player("malena@gmail.com" , "Malena", passwordEncoder().encode("123"));
             playerRepository.save(player1);
             playerRepository.save(player2);
             playerRepository.save(player3);
             playerRepository.save(player4);

             //creeamos y guardamos a los juegos con la hora en el repositorio
             Game game1 = new Game();
             Game game2 = new Game();
             game2.setCreated(Date.from(game1.getCreated().toInstant().plusSeconds(3600)));
             Game game3 = new Game();
             game3.setCreated(Date.from(game1.getCreated().toInstant().plusSeconds(7200)));

             gameRepository.save(game1);
             gameRepository.save(game2);
             gameRepository.save(game3);


             //"unimos" a los jugadores coon los juegos y lo guardamos en el repositorio
             GamePlayer gamePlayer1 = new GamePlayer(player1,	game1);
             GamePlayer gamePlayer2 = new GamePlayer(player2,	game1);
             GamePlayer gamePlayer3 = new GamePlayer(player3,    game2);
             GamePlayer gamePlayer4 = new GamePlayer(player4,    game2);


             gamePlayerRepository.save(gamePlayer1);
             gamePlayerRepository.save(gamePlayer2);
             gamePlayerRepository.save(gamePlayer3);
             gamePlayerRepository.save(gamePlayer4);



             //Ships para GamePlayer1
             Ship destructor1 = new Ship("destroyer", gamePlayer1, Arrays.asList("A1", "A2", "A3"));
             Ship submarine1 = new Ship ("submarine", gamePlayer1, Arrays.asList("C1", "C2", "C3"));
             Ship carrier1 = new Ship ("carrier", gamePlayer1, Arrays.asList("B1", "B2", "B3", "B4", "B5"));
             Ship battleship1 = new Ship ("battleship", gamePlayer1, Arrays.asList("D1", "D2", "D3", "D4"));
             Ship patrolboat1 = new Ship ("patrolboat", gamePlayer1, Arrays.asList("F1", "F2"));

             //Ships para el GamePlayer2
             Ship destructor2 = new Ship("destroyer", gamePlayer2, Arrays.asList("A4", "A5", "A6"));
             Ship submarine2 = new Ship ("submarine", gamePlayer2, Arrays.asList("C4", "C5", "C6"));
             Ship carrier2 = new Ship ("carrier", gamePlayer2, Arrays.asList("B4", "B5", "B6", "B7", "B8"));
             Ship battleship2 = new Ship ("battleship", gamePlayer2, Arrays.asList("D4", "D5", "D6", "D7"));
             Ship patrolboat2 = new Ship ("patrolboat", gamePlayer2, Arrays.asList("F7", "F8"));


             //Ships para el GamePlayer3
             Ship destructor3 = new Ship("destroyer", gamePlayer3, Arrays.asList("A6", "A7", "A8"));
             Ship submarine3 = new Ship ("submarine", gamePlayer3, Arrays.asList("C6", "C7", "C8"));
             Ship carrier3 = new Ship ("carrier", gamePlayer3, Arrays.asList("B6", "B7", "B8", "B9", "B10"));
             Ship battleship3 = new Ship ("battleship", gamePlayer3, Arrays.asList("H6", "H7", "H8", "H9"));
             Ship patrolboat3 = new Ship ("patrolboat", gamePlayer3, Arrays.asList("I4", "I5"));


             //Ships para el GamePlayer4
             Ship destructor4 = new Ship("destroyer", gamePlayer4, Arrays.asList("A6", "A7", "A8"));
             Ship submarine4 = new Ship ("submarine", gamePlayer4, Arrays.asList("C1", "C2", "C3"));
             Ship carrier4 = new Ship ("carrier", gamePlayer4, Arrays.asList("B6", "B7", "B8", "B9", "B10"));
             Ship battleship4 = new Ship ("battleship", gamePlayer4, Arrays.asList("D6", "D7", "D8", "D9"));
             Ship patrolboat4 = new Ship ("patrolboat", gamePlayer4, Arrays.asList("J5", "J6"));


             //Guardo en el repositorio los Ships con sus jugadores correspondientes
             //Ships guardados del GamePlayer1
             shipRepository.save(destructor1);
             shipRepository.save(submarine1);
             shipRepository.save(carrier1);
             shipRepository.save(battleship1);
             shipRepository.save(patrolboat1);

             //Ships guardados para el GamePlayer2
             shipRepository.save(destructor2);
             shipRepository.save(submarine2);
             shipRepository.save(carrier2);
             shipRepository.save(battleship2);
             shipRepository.save(patrolboat2);


             //Ships guardados para el GamePlayer3
             shipRepository.save(destructor3);
             shipRepository.save(submarine3);
             shipRepository.save(carrier3);
             shipRepository.save(battleship3);
             shipRepository.save(patrolboat3);


             //Ships guardados para el GamePlayer4
             shipRepository.save(destructor4);
             shipRepository.save(submarine4);
             shipRepository.save(carrier4);
             shipRepository.save(battleship4);
             shipRepository.save(patrolboat4);


             *//*Salvo s1 = new Salvo(gamePlayer1, Arrays.asList("A1", "A2", "A3"), 1);
			Salvo s2 = new Salvo(gamePlayer2, Arrays.asList("C1", "C2", "C3"), 1);
			Salvo s3 = new Salvo(gamePlayer3, Arrays.asList("E1", "E2", "E3"), 2);
			Salvo s4 = new Salvo(gamePlayer4, Arrays.asList("G1", "G2", "G3"), 2);

			salvoRepository.save(s1);
			salvoRepository.save(s2);
			salvoRepository.save(s3);
			salvoRepository.save(s4);*//*
		 *//*
        Score score1 = new Score(1.0, new Date(),player1, game1);
        Score score2 = new Score(0.0, new Date(),player2, game1);


        scoreRepository.save(score1);
        scoreRepository.save(score2);*//*

		};

	}*/
}
		@Configuration
		class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

			@Autowired
			PlayerRepository playereRepository;

			@Override
			public void init(AuthenticationManagerBuilder auth) throws Exception {
				auth.userDetailsService(inputName -> {
					Player player = playereRepository.findByEmail(inputName);
					if (player != null) {
						return new User(player.getEmail(), player.getPassword(),
								AuthorityUtils.createAuthorityList("USER"));
					} else {
						throw new UsernameNotFoundException("Unknown user: " + inputName);
					}
				});
			}
		}

		@Configuration
		@EnableWebSecurity
		class WebSecurityConfig extends WebSecurityConfigurerAdapter {

			@Override
			protected void configure(HttpSecurity http) throws Exception {
				http.authorizeRequests()
						.antMatchers("/web/**").permitAll()
						.antMatchers("/api/game_view/*").hasAuthority("USER")
						.antMatchers("/h2-console/**").permitAll()
						.antMatchers("/api/games").permitAll();

				http.formLogin()
						.usernameParameter("name")
						.passwordParameter("pwd")
						.loginPage("/api/login");

				http.logout().logoutUrl("/api/logout");

				// turn off checking for CSRF tokens
				http.csrf().disable();

				// if user is not authenticated, just send an authentication failure response
				http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

				// if login is successful, just clear the flags asking for authentication
				http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

				// if login fails, just send an authentication failure response
				http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

				// if logout is successful, just send a success response
				http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
			}

			private void clearAuthenticationAttributes(HttpServletRequest request) {
				HttpSession session = request.getSession(false);
				if (session != null) {
					session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
				}
			}
		}

