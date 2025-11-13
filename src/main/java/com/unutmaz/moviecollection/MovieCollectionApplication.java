package com.unutmaz.moviecollection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Movie Collection.
 * Esta é a classe de inicialização do Spring Boot que configura e inicia
 * a aplicação web para gerenciamento de coleção de filmes.
 *
 * A aplicação fornece funcionalidades para:
 * - Gerenciamento de filmes (CRUD)
 * - Gerenciamento de atores
 * - Autenticação e autorização de usuários
 * - APIs REST para acesso programático
 * - Interface web para interação com usuários
 *
 * Tecnologias utilizadas:
 * - Spring Boot (framework principal)
 * - Spring Security (autenticação/autorização)
 * - Spring Data JPA (persistência de dados)
 * - H2 Database (banco de dados em memória)
 * - Thymeleaf (templates web)
 */
@SpringBootApplication
public class MovieCollectionApplication {

	/**
	 * Método principal que inicia a aplicação Spring Boot.
	 *
	 * @param args Argumentos de linha de comando (não utilizados).
	 */
	public static void main(String[] args) {
		SpringApplication.run(MovieCollectionApplication.class, args);
	}

}
