package com.unutmaz.moviecollection;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Classe de configuração de segurança específica para o console H2 Database.
 * Esta configuração permite acesso irrestrito ao console H2 durante o desenvolvimento,
 * desabilitando proteções de segurança que poderiam impedir o acesso ao banco de dados H2.
 *
 * A anotação @Order(value=0) garante que esta configuração seja aplicada antes de outras
 * configurações de segurança, permitindo que as regras específicas do H2 tenham prioridade.
 *
 * Funcionalidades:
 * - Permite acesso irrestrito a todas as requisições para /h2-console/**.
 * - Desabilita proteção CSRF para o console H2.
 * - Desabilita frame options para permitir que o console seja exibido em iframes.
 *
 * Nota: Esta configuração deve ser usada apenas em ambientes de desenvolvimento.
 * Em produção, o console H2 deve ser desabilitado ou protegido adequadamente.
 */
@Configuration
@Order(value=0)
public class H2SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * Configura as regras de segurança específicas para o console H2.
	 * Permite acesso irrestrito e desabilita proteções que interferem no funcionamento do console.
	 *
	 * @param http O objeto HttpSecurity para configurar as regras de segurança.
	 * @throws Exception Se ocorrer um erro durante a configuração.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.antMatcher("/h2-console/**").authorizeRequests().anyRequest().permitAll(); // Acesso irrestrito ao console H2
		http.csrf().disable(); // Desabilita CSRF para o console
		http.headers().frameOptions().disable(); // Permite exibição em iframes
	}
}
