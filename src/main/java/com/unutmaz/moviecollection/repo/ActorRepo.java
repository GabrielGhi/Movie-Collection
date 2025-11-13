package com.unutmaz.moviecollection.repo;

import java.util.List;

import com.unutmaz.moviecollection.model.Actor;
import com.unutmaz.moviecollection.model.Movie;
import com.unutmaz.moviecollection.model.User;

/**
 * Interface do repositório para operações relacionadas a Atores.
 * Define métodos para CRUD de atores, busca por filme e registro de usuários.
 * Esta interface é implementada por ActorRepoJPA para operações com banco de dados.
 */
public interface ActorRepo {

	/**
	 * Busca todos os atores que participam de um filme específico.
	 *
	 * @param movie Filme para o qual buscar os atores.
	 * @return Lista de atores do filme.
	 */
	List<Actor> findActorsByMovie(Movie movie);

	/**
	 * Busca todos os atores cadastrados no sistema.
	 *
	 * @return Lista de todos os atores.
	 */
	List<Actor> findActors();

	/**
	 * Busca um ator específico pelo seu ID.
	 *
	 * @param id ID do ator a ser buscado.
	 * @return Ator encontrado ou null se não existir.
	 */
	Actor findActorById(Long id);

	/**
	 * Cria um novo ator no sistema.
	 *
	 * @param actor Ator a ser criado.
	 */
	void create(Actor actor);

	/**
	 * Atualiza um ator existente.
	 *
	 * @param actor Ator com dados atualizados.
	 * @return Ator atualizado.
	 */
	Actor update(Actor actor);

	/**
	 * Exclui um ator pelo seu ID.
	 *
	 * @param actor_id ID do ator a ser excluído.
	 */
	void delete(Long actor_id);

	/**
	 * Registra um novo usuário no sistema.
	 * Nota: Este método parece estar fora do escopo de um repositório de atores,
	 * mas pode estar aqui por questões de organização.
	 *
	 * @param user Usuário a ser registrado.
	 */
	void registerUser(User user);
}
