package com.unutmaz.moviecollection.repo;

import java.util.List;

import com.unutmaz.moviecollection.model.Movie;

/**
 * Interface do repositório para operações relacionadas a Filmes.
 * Define métodos para CRUD de filmes e buscas por diferentes critérios.
 * Esta interface é implementada por MovieRepoJPA para operações com banco de dados.
 */
public interface MovieRepo {

	/**
	 * Busca todos os filmes cadastrados no sistema.
	 *
	 * @return Lista de todos os filmes.
	 */
	List<Movie> findAll();

	/**
	 * Busca um filme específico pelo seu ID.
	 *
	 * @param id ID do filme a ser buscado.
	 * @return Filme encontrado ou null se não existir.
	 */
	Movie findById(Long id);

	/**
	 * Busca filmes por nome (pode ser parcial).
	 *
	 * @param name Nome ou parte do nome do filme.
	 * @return Lista de filmes que correspondem ao critério de busca.
	 */
	List<Movie> findByName(String name);

	/**
	 * Busca filmes por categoria/gênero.
	 *
	 * @param category Categoria do filme.
	 * @return Lista de filmes da categoria especificada.
	 */
	List<Movie> findByCategory(String category);

	/**
	 * Busca filmes que contenham um ator específico no elenco.
	 *
	 * @param actor Nome do ator.
	 * @return Lista de filmes que têm o ator no elenco.
	 */
	List<Movie> findByActor(String actor);

	/**
	 * Cria um novo filme no sistema.
	 *
	 * @param movie Filme a ser criado.
	 */
	void create(Movie movie);

	/**
	 * Atualiza um filme existente.
	 *
	 * @param movie Filme com dados atualizados.
	 * @return Filme atualizado.
	 */
	Movie update(Movie movie);

	/**
	 * Exclui um filme pelo seu ID.
	 *
	 * @param id ID do filme a ser excluído.
	 */
	void delete(Long id);
}
