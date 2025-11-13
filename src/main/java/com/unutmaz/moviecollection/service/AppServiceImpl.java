package com.unutmaz.moviecollection.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unutmaz.moviecollection.exception.MovieNotFoundException;
import com.unutmaz.moviecollection.model.Actor;
import com.unutmaz.moviecollection.model.Movie;
import com.unutmaz.moviecollection.model.User;
import com.unutmaz.moviecollection.repo.ActorRepo;
import com.unutmaz.moviecollection.repo.MovieRepo;

/**
 * Implementação do serviço da aplicação Movie Collection.
 * Esta classe implementa a lógica de negócio definida na interface AppService.
 * Coordena operações entre os repositórios de filmes e atores, aplicando
 * validações e regras de negócio quando necessário.
 *
 * Todas as operações são transacionais para garantir consistência dos dados.
 */
@Service
@Transactional
public class AppServiceImpl implements AppService {

	/**
	 * Repositório para operações com filmes.
	 */
	private MovieRepo movieRepo;

	/**
	 * Repositório para operações com atores.
	 */
	private ActorRepo actorRepo;

	/**
	 * Injeção do repositório de filmes via setter.
	 *
	 * @param movieRepo Instância do MovieRepo.
	 */
	@Autowired
	public void setMovieRepo(MovieRepo movieRepo) {
		this.movieRepo = movieRepo;
	}

	/**
	 * Injeção do repositório de atores via setter.
	 *
	 * @param actorRepo Instância do ActorRepo.
	 */
	@Autowired
	public void setActorRepo(ActorRepo actorRepo) {
		this.actorRepo = actorRepo;
	}

	/**
	 * Busca todos os filmes cadastrados.
	 *
	 * @return Lista de todos os filmes.
	 */
	@Override
	public List<Movie> findMovies() {
		return movieRepo.findAll();
	}

	/**
	 * Busca filmes por nome.
	 *
	 * @param name Nome ou parte do nome do filme.
	 * @return Lista de filmes que correspondem ao critério.
	 */
	@Override
	public List<Movie> findMoviesByName(String name) {
		return movieRepo.findByName(name);
	}

	/**
	 * Busca um filme específico pelo ID, lançando exceção se não encontrado.
	 *
	 * @param id ID do filme.
	 * @return Filme encontrado.
	 * @throws MovieNotFoundException Se o filme não existir.
	 */
	@Override
	public Movie findMovie(Long id) throws MovieNotFoundException {
		Movie movie = movieRepo.findById(id);
		if (movie == null) throw new MovieNotFoundException("Movie not found with id: " + id);
		return movie;
	}

	/**
	 * Cria um novo filme.
	 *
	 * @param movie Filme a ser criado.
	 */
	@Override
	public void createMovie(Movie movie) {
		movieRepo.create(movie);
	}

	/**
	 * Cria um novo ator.
	 *
	 * @param actor Ator a ser criado.
	 */
	@Override
	public void createActor(Actor actor) {
		actorRepo.create(actor);
	}

	/**
	 * Atualiza um filme existente.
	 *
	 * @param movie Filme com dados atualizados.
	 */
	@Override
	public void update(Movie movie) {
		movieRepo.update(movie);
	}

	/**
	 * Atualiza um ator existente.
	 *
	 * @param actor Ator com dados atualizados.
	 */
	@Override
	public void update(Actor actor) {
		actorRepo.update(actor);
	}

	/**
	 * Exclui um filme pelo ID.
	 *
	 * @param id ID do filme a ser excluído.
	 */
	@Override
	public void deleteMovie(Long id) {
		movieRepo.delete(id);
	}

	/**
	 * Exclui um ator pelo ID.
	 *
	 * @param actor_id ID do ator a ser excluído.
	 */
	@Override
	public void deleteActor(Long actor_id) {
		actorRepo.delete(actor_id);
	}

	/**
	 * Busca filmes por categoria.
	 *
	 * @param category Categoria do filme.
	 * @return Lista de filmes da categoria especificada.
	 */
	@Override
	public List<Movie> findMoviesByCategory(String category) {
		return movieRepo.findByCategory(category);
	}

	/**
	 * Busca filmes que contenham um ator específico.
	 *
	 * @param actor Nome do ator.
	 * @return Lista de filmes com o ator no elenco.
	 */
	@Override
	public List<Movie> findMoviesByActor(String actor) {
		return movieRepo.findByActor(actor);
	}

	/**
	 * Busca todos os atores cadastrados.
	 *
	 * @return Lista de todos os atores.
	 */
	@Override
	public List<Actor> findActors() {
		return actorRepo.findActors();
	}

	/**
	 * Busca um ator específico pelo ID.
	 *
	 * @param id ID do ator.
	 * @return Ator encontrado ou null se não existir.
	 */
	@Override
	public Actor findActor(Long id) {
		return actorRepo.findActorById(id);
	}

	/**
	 * Busca atores que participam de um filme específico.
	 *
	 * @param movie Filme para o qual buscar atores.
	 * @return Lista de atores do filme.
	 */
	@Override
	public List<Actor> findActorsByMovie(Movie movie) {
		return actorRepo.findActorsByMovie(movie);
	}

	/**
	 * Registra um novo usuário no sistema.
	 *
	 * @param user Usuário a ser registrado.
	 */
	@Override
	public void registerUser(User user) {
		actorRepo.registerUser(user);
	}

}
