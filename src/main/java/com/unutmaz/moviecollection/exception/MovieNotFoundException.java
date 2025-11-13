package com.unutmaz.moviecollection.exception;

/**
 * Exceção personalizada lançada quando um filme não é encontrado no sistema.
 * Esta classe estende RuntimeException e é usada para indicar que uma operação
 * tentou acessar um filme que não existe no banco de dados.
 *
 * É utilizada principalmente nos controladores REST e serviços para sinalizar
 * que um filme com o ID especificado não foi localizado.
 */
public class MovieNotFoundException extends RuntimeException {

	/**
	 * Construtor que aceita uma mensagem descrevendo o erro.
	 *
	 * @param message Mensagem detalhando o motivo da exceção.
	 */
	public MovieNotFoundException(String message) {
		super(message);
	}

}
