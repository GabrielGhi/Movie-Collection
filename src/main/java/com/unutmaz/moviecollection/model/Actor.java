package com.unutmaz.moviecollection.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entidade JPA representando um Ator no sistema Movie Collection.
 * Esta classe mapeia a tabela "t_actors" no banco de dados e representa
 * atores que participam de filmes.
 *
 * Relacionamentos:
 * - Muitos-para-muitos com Movie: Um ator pode participar de vários filmes,
 *   e um filme pode ter vários atores no elenco.
 *
 * A anotação @JsonIgnoreProperties ignora o campo "playedIn" na serialização JSON
 * para evitar recursão infinita ao serializar objetos Movie-Actor.
 */
@Entity
@Table(name="t_actors")
@JsonIgnoreProperties(value = { "playedIn" })
public class Actor {

	/**
	 * ID único do ator, gerado automaticamente pelo banco de dados.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long actor_id;

	/**
	 * Nome do ator.
	 */
	@Column(name="actor_name")
	private String actor_name;

	/**
	 * Conjunto de filmes nos quais o ator participou.
	 * Relacionamento bidirecional muitos-para-muitos com Movie.
	 * FetchType.LAZY para carregamento preguiçoso.
	 */
	@ManyToMany(fetch=FetchType.LAZY, mappedBy= "cast")
	private Set<Movie> playedIn = new HashSet<>();

	/**
	 * Obtém o ID do ator.
	 *
	 * @return ID do ator.
	 */
	public Long getActor_id() {
		return actor_id;
	}

	/**
	 * Define o ID do ator.
	 *
	 * @param actor_id Novo ID do ator.
	 */
	public void setActor_id(Long actor_id) {
		this.actor_id = actor_id;
	}

	/**
	 * Obtém o nome do ator.
	 *
	 * @return Nome do ator.
	 */
	public String getActor_name() {
		return actor_name;
	}

	/**
	 * Define o nome do ator.
	 *
	 * @param actor_name Novo nome do ator.
	 */
	public void setActor_name(String actor_name) {
		this.actor_name = actor_name;
	}

	/**
	 * Obtém o conjunto de filmes nos quais o ator participou.
	 *
	 * @return Conjunto de filmes.
	 */
	public Set<Movie> getPlayedIn() {
		return playedIn;
	}

	/**
	 * Define o conjunto de filmes nos quais o ator participou.
	 *
	 * @param playedIn Novo conjunto de filmes.
	 */
	public void setPlayedIn(Set<Movie> playedIn) {
		this.playedIn = playedIn;
	}

	/**
	 * Adiciona um filme ao conjunto de filmes do ator.
	 *
	 * @param movie Filme a ser adicionado.
	 */
	public void addMovie(Movie movie) {
		this.playedIn.add(movie);
	}

	/**
	 * Retorna uma representação em string do ator.
	 *
	 * @return String contendo ID e nome do ator.
	 */
	@Override
	public String toString() {
		return "Actor [actor_id=" + actor_id + ", actor_name=" + actor_name + "]";
	}

}
