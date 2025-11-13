package com.unutmaz.moviecollection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Classe de configuração de segurança da aplicação Spring Security.
 * Esta classe configura a autenticação e autorização para proteger os endpoints da aplicação.
 * Extende WebSecurityConfigurerAdapter para personalizar as configurações de segurança.
 *
 * Funcionalidades principais:
 * - Permite acesso público a recursos estáticos e páginas de login/registro.
 * - Restringe acesso à listagem de filmes para usuários autenticados com papel 'USER'.
 * - Restringe operações de edição, criação e exclusão de filmes para usuários com papel 'ADMIN'.
 * - Configura formulário de login personalizado e logout.
 * - Usa autenticação baseada em JDBC com dados do banco de dados configurado.
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * Serviço para carregar detalhes do usuário durante a autenticação.
	 * Injetado automaticamente pelo Spring.
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Fonte de dados (DataSource) para conexão com o banco de dados.
	 * Usado para autenticação JDBC.
	 */
	@Autowired
	private DataSource dataSource;

	/**
	 * Configura as regras de autorização para as requisições HTTP.
	 * Define quais URLs são públicas, quais requerem autenticação e quais papéis são necessários.
	 *
	 * @param http O objeto HttpSecurity para configurar as regras de segurança.
	 * @throws Exception Se ocorrer um erro durante a configuração.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/**/favicon.ico", "/css/**", "/js/**", "/images/**", "/webjars/**", "/login", "/register").permitAll() // Recursos públicos
			.antMatchers("/movies/list").access("hasRole('USER')") // Apenas usuários com papel USER
			.antMatchers("/movies/edit/**", "/movies/cast", "/movies/new/**", "/movies/update/**", "/movies/delete/**").access("hasRole('ADMIN')") // Apenas ADMIN
			.anyRequest().authenticated() // Todas as outras requisições requerem autenticação
			.and()
			.formLogin().loginPage("/login") // Página de login personalizada
			.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/login"); // Configuração de logout
	}

	/**
	 * Configura o AuthenticationManagerBuilder para usar autenticação JDBC.
	 * Os usuários são autenticados consultando o banco de dados via DataSource.
	 *
	 * @param auth O AuthenticationManagerBuilder para configurar a autenticação.
	 * @throws Exception Se ocorrer um erro durante a configuração.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource);
	}
}
