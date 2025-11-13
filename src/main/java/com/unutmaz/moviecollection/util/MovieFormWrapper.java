package com.unutmaz.moviecollection.util;

import java.sql.Date;

/**
 * Classe wrapper para formulários de filmes.
 * Esta classe auxilia na transferência de dados entre formulários web e
 * objetos Movie, facilitando a manipulação de dados de filmes em operações
 * de criação e edição através de interfaces web.
 *
 * O campo castRef armazena referências aos atores do filme em formato de texto,
 * que pode ser processado posteriormente para associar atores ao filme.
 */
public class MovieFormWrapper {

	/**
	 * Nome/título do filme.
	 */
	private String name;

	/**
	 * Data de lançamento do filme.
	 */
	private Date date;

	/**
	 * Categoria/gênero do filme.
	 */
	private String category;

	/**
	 * Descrição/sinopse do filme.
	 */
	private String description;

	/**
	 * Avaliação/classificação do filme.
	 */
	private float rating;

	/**
	 * URL ou caminho da imagem/poster do filme.
	 */
	private String image;

	/**
	 * Referência textual aos atores do filme (ex: IDs separados por vírgula).
	 */
	private String castRef;

	/**
	 * Construtor que inicializa o wrapper com uma referência de elenco.
	 *
	 * @param text Referência textual aos atores.
	 */
	public MovieFormWrapper(String text) {
		this.castRef = text;
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
	 * Obtém a referência textual aos atores.
	 *
	 * @return Referência aos atores.
	 */
	public String getCastRef() {
		return castRef;
	}

	/**
	 * Define a referência textual aos atores.
	 *
	 * @param castRef Nova referência aos atores.
	 */
	public void setCastRef(String castRef) {
		this.castRef = castRef;
	}


}
