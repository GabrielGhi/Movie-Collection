package com.unutmaz.moviecollection.model;

/**
 * Classe modelo representando um Usuário no sistema Movie Collection.
 * Esta classe não é uma entidade JPA, mas é usada para autenticação e autorização
 * com Spring Security. Os dados dos usuários são armazenados no banco de dados
 * através de queries JDBC personalizadas na configuração de segurança.
 *
 * Papéis suportados:
 * - ROLE_USER: Usuário padrão com acesso limitado.
 * - ROLE_ADMIN: Administrador com acesso completo.
 *
 * Por padrão, novos usuários recebem o papel ROLE_USER.
 */
public class User {

	/**
	 * Nome de usuário único para autenticação.
	 */
	private String username;

	/**
	 * Senha do usuário (deve ser armazenada de forma criptografada).
	 */
	private String password;

	/**
	 * Indica se a conta do usuário está ativa/habilitada.
	 */
	private boolean enabled;

	/**
	 * Papel/função do usuário no sistema (ex: ROLE_USER, ROLE_ADMIN).
	 */
	private String role;

	/**
	 * Construtor padrão que define o papel como ROLE_USER.
	 */
	public User() {
		this.role = "ROLE_USER";
	}

	/**
	 * Obtém o nome de usuário.
	 *
	 * @return Nome de usuário.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Define o nome de usuário.
	 *
	 * @param username Novo nome de usuário.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Obtém a senha do usuário.
	 *
	 * @return Senha do usuário.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Define a senha do usuário.
	 *
	 * @param password Nova senha do usuário.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Obtém o papel do usuário.
	 *
	 * @return Papel do usuário.
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Define o papel do usuário.
	 *
	 * @param role Novo papel do usuário.
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Verifica se a conta do usuário está habilitada.
	 *
	 * @return true se habilitada, false caso contrário.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Define se a conta do usuário está habilitada.
	 *
	 * @param enabled true para habilitar, false para desabilitar.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



}
