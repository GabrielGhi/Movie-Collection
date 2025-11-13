package com.unutmaz.moviecollection.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entidade JPA representando um Filme no sistema Movie Collection.
 * Esta classe mapeia a tabela "t_movies" no banco de dados e representa
 * filmes com suas informações básicas e elenco de atores.
 *
 * Relacionamentos:
 * - Muitos-para-muitos com Actor: Um filme pode ter vários atores no elenco,
 *   e um ator pode participar de vários filmes.
 *
 * A anotação @JsonIgnoreProperties ignora o campo "castRef" na serialização JSON
 * (provavelmente um campo auxiliar não persistido).
 */
@Entity
@Table(name="t_movies")
@JsonIgnoreProperties(value = { "castRef" })
public class Movie {

	/**
	 * ID único do filme, gerado automaticamente pelo banco de dados.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nome/título do filme.
	 */
	@Column(name="name")
	private String name;

	/**
	 * Data de lançamento do filme.
	 */
	@Column(name="release_date")
	private Date date;

	/**
	 * Categoria/gênero do filme.
	 */
	@Column(name="category")
	private String category;

	/**
	 * Descrição/sinopse do filme.
	 */
	@Column(name="description")
	private String description;

	/**
	 * URL ou caminho da imagem/poster do filme.
	 */
	@Column(name="image")
	private String image;

	/**
	 * Avaliação/classificação do filme (ex: nota de 0 a 10).
	 */
	@Column(name="rating")
	private float rating;

	/**
	 * Conjunto de atores que participam do filme.
	 * Relacionamento muitos-para-muitos com Actor.
	 * Usa tabela de junção "movie_actors" para mapear o relacionamento.
	 * FetchType.LAZY para carregamento preguiçoso.
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "movie_actors",
			joinColumns = @JoinColumn(name="movie_id"),
			inverseJoinColumns = @JoinColumn(name="actor_id")
	)
	private Set<Actor> cast = new HashSet<>();

	/**
	 * Obtém o ID do filme.
	 *
	 * @return ID do filme.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o ID do filme.
	 *
	 * @param id Novo ID do filme.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtém o nome do filme.
	 *
	 * @return Nome do filme.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define o nome do filme.
	 *
	 * @param name Novo nome do filme.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtém a data de lançamento do filme.
	 *
	 * @return Data de lançamento.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Define a data de lançamento do filme.
	 *
	 * @param date Nova data de lançamento.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Obtém a categoria do filme.
	 *
	 * @return Categoria do filme.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Define a categoria do filme.
	 *
	 * @param category Nova categoria do filme.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Obtém a descrição do filme.
	 *
	 * @return Descrição do filme.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Define a descrição do filme.
	 *
	 * @param description Nova descrição do filme.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Obtém a avaliação do filme.
	 *
	 * @return Avaliação do filme.
	 */
	public float getRating() {
		return rating;
	}

	/**
	 * Define a avaliação do filme.
	 *
	 * @param rating Nova avaliação do filme.
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}

	/**
	 * Obtém o conjunto de atores do filme.
	 *
	 * @return Conjunto de atores.
	 */
	public Set<Actor> getCast() {
		return cast;
	}

	/**
	 * Define o conjunto de atores do filme.
	 *
	 * @param cast Novo conjunto de atores.
	 */
	public void setCast(Set<Actor> cast) {
		this.cast = cast;
	}

	/**
	 * Adiciona um ator ao elenco do filme.
	 *
	 * @param actor Ator a ser adicionado.
	 */
	public void addActor(Actor actor) {
		this.cast.add(actor);
	}

	/**
	 * Obtém a URL da imagem do filme.
	 *
	 * @return URL da imagem.
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Define a URL da imagem do filme.
	 *
	 * @param image Nova URL da imagem.
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Retorna uma representação em string do filme, incluindo seus atores.
	 *
	 * @return String contendo todas as informações do filme.
	 */
	@Override
	public String toString() {
		String castString = "";
		for (Actor actor : this.cast) {
			castString += actor.toString();
		}

		return "Movie [id=" + id + ", name=" + name + ", date=" + date + ", category=" + category + ", description="
				+ description + ", rating=" + rating + ", cast=" + castString + "]";
	}


}
