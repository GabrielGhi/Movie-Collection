package com.unutmaz.moviecollection.service;

import java.util.List;

import com.unutmaz.moviecollection.exception.MovieNotFoundException;
import com.unutmaz.moviecollection.model.Actor;
import com.unutmaz.moviecollection.model.Movie;
import com.unutmaz.moviecollection.model.User;

/**
 * Interface do serviço da aplicação Movie Collection.
 * Define métodos de negócio para operações com filmes, atores e usuários.
 * Esta interface é implementada por AppServiceImpl.
 *
 * Centraliza a lógica de negócio e coordena operações entre repositórios.
 */
public interface AppService {

	/**
	 * Busca todos os atores cadastrados.
	 *
	 * @return Lista de todos os atores.
	 */
	List<Actor> findActors();

	/**
	 * Busca atores que participam de um filme específico.
	 *
	 * @param movie Filme para o qual buscar atores.
	 * @return Lista de atores do filme.
	 */
	List<Actor> findActorsByMovie(Movie movie);

	/**
	 * Busca todos os filmes cadastrados.
	 *
	 * @return Lista de todos os filmes.
	 */
	List<Movie> findMovies();

	/**
	 * Busca filmes por nome.
	 *
	 * @param name Nome ou parte do nome do filme.
	 * @return Lista de filmes que correspondem ao critério.
	 */
	List<Movie> findMoviesByName(String name);

	/**
	 * Busca filmes por categoria.
	 *
	 * @param category Categoria do filme.
	 * @return Lista de filmes da categoria especificada.
	 */
	List<Movie> findMoviesByCategory(String category);

	/**
	 * Busca filmes que contenham um ator específico.
	 *
	 * @param actor Nome do ator.
	 * @return Lista de filmes com o ator no elenco.
	 */
	List<Movie> findMoviesByActor(String actor);

	/**
	 * Busca um filme específico pelo ID.
	 *
	 * @param id ID do filme.
	 * @return Filme encontrado.
	 * @throws MovieNotFoundException Se o filme não for encontrado.
	 */
	Movie findMovie(Long id) throws MovieNotFoundException;

	/**
	 * Busca um ator específico pelo ID.
	 *
	 * @param id ID do ator.
	 * @return Ator encontrado ou null se não existir.
	 */
	Actor findActor(Long id);

	/**
	 * Cria um novo filme.
	 *
	 * @param movie Filme a ser criado.
	 */
	void createMovie(Movie movie);

	/**
	 * Cria um novo ator.
	 *
	 * @param actor Ator a ser criado.
	 */
	void createActor(Actor actor);

	/**
	 * Atualiza um filme existente.
	 *
	 * @param movie Filme com dados atualizados.
	 */
	void update(Movie movie);

	/**
	 * Atualiza um ator existente.
	 *
	 * @param actor Ator com dados atualizados.
	 */
	void update(Actor actor);

	/**
	 * Exclui um filme pelo ID.
	 *
	 * @param id ID do filme a ser excluído.
	 */
	void deleteMovie(Long id);

	/**
	 * Exclui um ator pelo ID.
	 *
	 * @param actor_id ID do ator a ser excluído.
	 */
	void deleteActor(Long actor_id);

	/**
	 * Registra um novo usuário no sistema.
	 *
	 * @param user Usuário a ser registrado.
	 */
	void registerUser(User user);
}
