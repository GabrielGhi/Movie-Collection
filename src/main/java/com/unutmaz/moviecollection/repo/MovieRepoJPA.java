package com.unutmaz.moviecollection.repo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.unutmaz.moviecollection.model.Movie;

/**
 * Implementação JPA do repositório de filmes.
 * Esta classe implementa a interface MovieRepo usando JPA/Hibernate para
 * operações de banco de dados relacionadas a filmes.
 *
 * Inclui buscas otimizadas por nome, categoria e ator, além das operações CRUD básicas.
 */
@Repository("movieRepository")
public class MovieRepoJPA implements MovieRepo {

	/**
	 * EntityManager injetado pelo Spring para operações de persistência.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Busca todos os filmes cadastrados.
	 *
	 * @return Lista de todos os filmes.
	 */
	@Override
	public List<Movie> findAll() {
		return entityManager.createQuery("from Movie", Movie.class).getResultList();
	}

	/**
	 * Busca um filme específico pelo seu ID.
	 *
	 * @param id ID do filme.
	 * @return Filme encontrado ou null se não existir.
	 */
	@Override
	public Movie findById(Long id) {
		return entityManager.find(Movie.class, id);
	}

	/**
	 * Busca filmes por nome usando busca case-insensitive com LIKE.
	 *
	 * @param name Nome ou parte do nome do filme.
	 * @return Lista de filmes que correspondem ao critério.
	 */
	@Override
	public List<Movie> findByName(String name) {
		String queryString = "from Movie WHERE upper(name) LIKE :name";
		return entityManager.createQuery(queryString, Movie.class)
				.setParameter("name", "%" + name.toUpperCase() + "%")
				.getResultList();
	}

	/**
	 * Cria um novo filme no banco de dados.
	 *
	 * @param movie Filme a ser persistido.
	 */
	@Override
	public void create(Movie movie) {
		entityManager.persist(movie);
	}

	/**
	 * Atualiza um filme existente no banco de dados.
	 *
	 * @param movie Filme com dados atualizados.
	 * @return Filme atualizado.
	 */
	@Override
	public Movie update(Movie movie) {
		return entityManager.merge(movie);
	}

	/**
	 * Exclui um filme do banco de dados pelo seu ID.
	 *
	 * @param id ID do filme a ser excluído.
	 */
	@Override
	public void delete(Long id) {
		entityManager.remove(entityManager.getReference(Movie.class, id));
	}

	/**
	 * Busca filmes por categoria usando busca case-insensitive exata.
	 *
	 * @param category Categoria do filme.
	 * @return Lista de filmes da categoria especificada.
	 */
	@Override
	public List<Movie> findByCategory(String category) {
		return entityManager.createQuery("from Movie WHERE upper(category) = :category", Movie.class)
				.setParameter("category", category.toUpperCase()).getResultList();
	}

	/**
	 * Busca filmes que contenham um ator específico no elenco.
	 * Esta implementação carrega todos os filmes e filtra em memória,
	 * o que pode não ser eficiente para grandes volumes de dados.
	 *
	 * @param actor Nome do ator (busca parcial, case-insensitive).
	 * @return Lista de filmes que têm o ator no elenco.
	 */
	@Override
	public List<Movie> findByActor(String actor) {
		List<Movie> movies = entityManager.createQuery("from Movie", Movie.class).getResultList();
		return movies.stream().filter(m -> m.getCast().stream().anyMatch(a -> a.getActor_name().toUpperCase().contains(actor.toUpperCase()))).collect(Collectors.toList());
	}

}
