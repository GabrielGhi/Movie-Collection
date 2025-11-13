package com.unutmaz.moviecollection.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.unutmaz.moviecollection.exception.MovieNotFoundException;
import com.unutmaz.moviecollection.model.Movie;
import com.unutmaz.moviecollection.service.AppService;

/**
 * Controlador REST da aplicação Movie Collection.
 * Esta classe fornece endpoints RESTful para acesso programático aos dados de filmes.
 * Permite operações de leitura (GET) para filmes, retornando dados em formato JSON.
 *
 * Funcionalidades:
 * - Listar todos os filmes (/rest/movies).
 * - Obter detalhes de um filme específico por ID (/rest/movie/{id}).
 *
 * Trata exceções como MovieNotFoundException e retorna códigos de status HTTP apropriados.
 */
@RestController
@RequestMapping("/rest")
public class AppRestController {

	/**
	 * Serviço da aplicação injetado automaticamente pelo Spring.
	 * Fornece métodos para operações de negócio relacionadas a filmes.
	 */
	@Autowired
	private AppService appService;

	/**
	 * Retorna uma lista de todos os filmes disponíveis.
	 *
	 * @return ResponseEntity contendo a lista de filmes e status HTTP 200 (OK).
	 */
	@RequestMapping(method=RequestMethod.GET, value="/movies")
	public ResponseEntity<List<Movie>> getMovies(){
		List<Movie> movies = appService.findMovies();
		return ResponseEntity.ok(movies);
	}

	/**
	 * Retorna os detalhes de um filme específico identificado pelo ID.
	 *
	 * @param id ID do filme a ser recuperado.
	 * @return ResponseEntity com o filme encontrado (status 200) ou erro apropriado.
	 */
	@RequestMapping(method=RequestMethod.GET, value="/movie/{id}")
	public ResponseEntity<Movie> getMovie(@PathVariable("id") Long id){
		try {
			Movie movie = appService.findMovie(id);
			return ResponseEntity.ok(movie);
		} catch (MovieNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
