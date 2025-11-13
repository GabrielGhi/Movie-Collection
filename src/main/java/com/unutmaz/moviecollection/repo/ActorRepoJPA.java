package com.unutmaz.moviecollection.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.unutmaz.moviecollection.model.Actor;
import com.unutmaz.moviecollection.model.Movie;
import com.unutmaz.moviecollection.model.User;

/**
 * Implementação JPA do repositório de atores.
 * Esta classe implementa a interface ActorRepo usando JPA/Hibernate para
 * operações de banco de dados relacionadas a atores.
 *
 * Também inclui método para registro de usuários, que foi implementado aqui
 * por conveniência, embora não esteja diretamente relacionado ao modelo de atores.
 */
@Repository("actorRepository")
public class ActorRepoJPA implements ActorRepo {

	/**
	 * EntityManager injetado pelo Spring para operações de persistência.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Cria um novo ator no banco de dados.
	 *
	 * @param actor Ator a ser persistido.
	 */
	@Override
	public void create(Actor actor) {
		entityManager.persist(actor);
	}

	/**
	 * Atualiza um ator existente no banco de dados.
	 *
	 * @param actor Ator com dados atualizados.
	 * @return Ator atualizado.
	 */
	@Override
	public Actor update(Actor actor) {
		return entityManager.merge(actor);
	}

	/**
	 * Exclui um ator do banco de dados pelo seu ID.
	 *
	 * @param actor_id ID do ator a ser excluído.
	 */
	@Override
	public void delete(Long actor_id) {
		entityManager.remove(entityManager.getReference(Actor.class, actor_id));
	}

	/**
	 * Busca todos os atores cadastrados.
	 *
	 * @return Lista de todos os atores.
	 */
	@Override
	public List<Actor> findActors() {
		return entityManager.createQuery("from Actor", Actor.class).getResultList();
	}

	/**
	 * Busca um ator específico pelo seu ID.
	 *
	 * @param id ID do ator.
	 * @return Ator encontrado ou null se não existir.
	 */
	@Override
	public Actor findActorById(Long id) {
		return entityManager.find(Actor.class, id);
	}

	/**
	 * Busca atores que participam de um filme específico.
	 * Nota: Esta implementação parece ter um problema - está fazendo query na tabela
	 * Movie_Actors mas retornando uma lista de atores, não IDs. Pode precisar de revisão.
	 *
	 * @param movie Filme para o qual buscar atores.
	 * @return Lista de atores do filme.
	 */
	@Override
	public List<Actor> findActorsByMovie(Movie movie) {
		String q = "SELECT * FROM Movie_Actors WHERE movie_id = " + movie.getId();
		return entityManager.createNativeQuery(q).getResultList();
	}

	/**
	 * Registra um novo usuário no sistema.
	 * Este método foi implementado aqui por conveniência, embora não esteja
	 * diretamente relacionado ao modelo de atores. Insere dados nas tabelas
	 * 'users' e 'authorities' do Spring Security.
	 *
	 * @param user Usuário a ser registrado.
	 */
	@Override
	public void registerUser(User user) {
		String q = "INSERT INTO users(username, password, enabled) VALUES('" + user.getUsername() + "', '{noop}" + user.getPassword() + "', " + user.isEnabled() + ");";
		String q2 = "INSERT INTO authorities(username, authority) VALUES('" + user.getUsername() + "', '" + user.getRole() + "');";
		entityManager.createNativeQuery(q).executeUpdate();
		entityManager.createNativeQuery(q2).executeUpdate();
	}
}
