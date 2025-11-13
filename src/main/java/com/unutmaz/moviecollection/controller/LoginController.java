package com.unutmaz.moviecollection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.unutmaz.moviecollection.model.User;
import com.unutmaz.moviecollection.service.AppService;

/**
 * Controlador responsável pelo gerenciamento de autenticação e registro de usuários.
 * Esta classe lida com as páginas de login e registro, permitindo que usuários
 * se autentiquem no sistema e criem novas contas.
 *
 * Funcionalidades:
 * - Exibição da página de login (/login).
 * - Exibição e processamento do formulário de registro (/register).
 * - Validação básica dos dados de registro (usuário e senha não vazios).
 * - Registro de novos usuários no sistema.
 *
 * Após o registro bem-sucedido, o usuário é redirecionado para a página de login.
 */
@Controller
public class LoginController {

	/**
	 * Serviço da aplicação injetado automaticamente pelo Spring.
	 * Fornece métodos para operações de negócio, incluindo registro de usuários.
	 */
	@Autowired
	private AppService appService;

	/**
	 * Exibe a página de login.
	 *
	 * @return Nome da view de login.
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * Exibe o formulário de registro de novo usuário.
	 *
	 * @param model Modelo para passar um objeto User vazio para o formulário.
	 * @return Nome da view de registro.
	 */
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	/**
	 * Processa o registro de um novo usuário.
	 * Valida se usuário e senha não estão vazios. Se válidos, registra o usuário
	 * e redireciona para login. Caso contrário, redireciona de volta ao registro.
	 *
	 * @param user Objeto User com os dados do novo usuário.
	 * @param model Modelo para passar dados para a view (não usado neste método).
	 * @return Redirecionamento para login (sucesso) ou registro (falha).
	 */
	@PostMapping("/register")
	public String registerConfirm(@ModelAttribute User user, Model model) {
		if (user.getUsername().length() <= 0 || user.getPassword().length() <= 0) {
			return "redirect:/register";
		}
		else {
			user.setEnabled(true);
			appService.registerUser(user);
			return "login";
		}

	}
}
