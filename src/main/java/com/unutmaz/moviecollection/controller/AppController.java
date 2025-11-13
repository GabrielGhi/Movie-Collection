package com.unutmaz.moviecollection.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unutmaz.moviecollection.model.Actor;
import com.unutmaz.moviecollection.model.Movie;
import com.unutmaz.moviecollection.service.AppService;
import com.unutmaz.moviecollection.util.MovieFormWrapper;
import com.unutmaz.moviecollection.util.TextForm;

/**
 * Controlador principal da aplicação Movie Collection.
 * Esta classe gerencia todas as requisições HTTP relacionadas aos filmes e atores,
 * incluindo listagem, criação, edição, exclusão e visualização de detalhes.
 *
 * Funcionalidades principais:
 * - Redirecionamento para a página principal.
 * - CRUD completo para filmes (criar, ler, atualizar, deletar).
 * - Gerenciamento do elenco de filmes (adicionar/remover atores).
 * - Busca e ordenação de filmes por nome, categoria, ator e avaliação.
 * - Controle de atores (criação e listagem).
 *
 * Usa o AppService para interagir com a camada de serviço e o banco de dados.
 */
@Controller
public class AppController {

	/**
	 * Serviço da aplicação injetado automaticamente pelo Spring.
	 * Fornece métodos para operações de negócio relacionadas a filmes e atores.
	 */
	@Autowired
	private AppService appService;

	/**
	 * Redireciona a raiz da aplicação para a lista de filmes.
	 *
	 * @return Redirecionamento para /movies/list.
	 */
	@GetMapping("")
	public String redirectToMain() {
		return "redirect:/movies/list";
	}

	/**
	 * Exibe o formulário de edição de um filme específico.
	 *
	 * @param id ID do filme a ser editado.
	 * @param model Modelo para passar dados para a view.
	 * @return Nome da view de edição.
	 */
	@RequestMapping(method=RequestMethod.GET, value="/movies/update/{id}")
	public String updateMovie(@PathVariable("id") Long id, Model model) {
		Movie movie = appService.findMovie(id);
		model.addAttribute("movie", movie);
		return "edit";
	}

	/**
	 * Processa a atualização do filme e redireciona para a página de edição do elenco.
	 *
	 * @param id ID do filme sendo atualizado.
	 * @param movie Objeto Movie com os dados atualizados.
	 * @param model Modelo para passar dados para a view.
	 * @return Nome da view de atualização do elenco.
	 */
	@PostMapping("/movies//edit/cast/{id}")
	public String movieUpdating(@PathVariable("id") Long id, @ModelAttribute Movie movie, Model model) {
		appService.update(movie);
		model.addAttribute("movie_id", movie.getId());
		model.addAttribute("actor", new Actor());
		model.addAttribute("actors", appService.findActors());
		model.addAttribute("textForm", new TextForm());
		return "updateCast";
	}

	/**
	 * Confirma a atualização do elenco do filme com os atores selecionados.
	 *
	 * @param id ID do filme.
	 * @param textForm Formulário contendo os IDs dos atores selecionados.
	 * @return Redirecionamento para a lista de filmes.
	 */
	@PostMapping("/movies//edit/confirm/{id}")
	public String confirmUpdate(@PathVariable("id") Long id, @ModelAttribute TextForm textForm) {
		Movie movie = appService.findMovie(id);
		List<Actor> actors = new ArrayList<>();
		for (Long actor_id : textForm.StrToIds()) {
			actors.add(appService.findActor(actor_id));
		}
		movie.getCast().addAll(actors);
		appService.update(movie);
		return "redirect:/movies/list";
	}

	/**
	 * Exclui um filme específico.
	 *
	 * @param id ID do filme a ser excluído.
	 * @return Redirecionamento para a lista de filmes.
	 */
	@RequestMapping(method=RequestMethod.GET, value="/movies/delete/{id}")
	public String deleteMovie(@PathVariable("id") Long id) {
		appService.deleteMovie(id);
		return "redirect:/movies/list";
	}

	/**
	 * Processa a criação inicial de um filme a partir de um formulário de texto.
	 *
	 * @param textForm Formulário contendo dados iniciais do filme.
	 * @param model Modelo para passar dados para a view.
	 * @return Nome da view de criação de filme.
	 */
	@PostMapping("/movies/new")
	public String creatingMovie(@ModelAttribute TextForm textForm, Model model) {
		model.addAttribute("movieWrapper", new MovieFormWrapper(textForm.getText()));
		model.addAttribute("stars", textForm.getText());
		return "newMovie";
	}

	/**
	 * Confirma a criação de um novo filme com todos os detalhes preenchidos.
	 *
	 * @param mfw Wrapper contendo todos os dados do filme.
	 * @param model Modelo para passar dados para a view.
	 * @return Redirecionamento para a lista de filmes.
	 */
	@PostMapping("/movies/confirm")
	public String confirmCreation(@ModelAttribute MovieFormWrapper mfw, Model model) {
		Movie movie = new Movie();
		movie.setName(mfw.getName());
		movie.setDate(mfw.getDate());
		movie.setCategory(mfw.getCategory());
		movie.setDescription(mfw.getDescription());
		movie.setImage(mfw.getImage());
		movie.setRating(mfw.getRating());
		List<Actor> cast = new ArrayList<>();
		for (String elem : mfw.getCastRef().split(",")) {
			cast.add(appService.findActor(Long.parseLong(elem)));
		}
		movie.getCast().addAll(cast);
		appService.createMovie(movie);
		return "redirect:/movies/list";
	}

	/**
	 * Exibe a página de controle de elenco (atores).
	 *
	 * @return ModelAndView com a lista de atores e formulários para criação.
	 */
	@GetMapping("/movies/cast")
	public ModelAndView castControl() {
		ModelAndView mav = new ModelAndView();
		List<Actor> actors = appService.findActors();
		mav.addObject("actors", actors);
		mav.addObject("actor", new Actor());
		mav.addObject("textForm", new TextForm());
		mav.setViewName("castEdit");
		return mav;
	}

	/**
	 * Cria um novo ator.
	 *
	 * @param actor Objeto Actor com os dados do novo ator.
	 * @return Redirecionamento para a página de controle de elenco.
	 */
	@PostMapping("/movies/new/actor")
	public String actorcreation(@ModelAttribute Actor actor) {
		appService.createActor(actor);
		return "redirect:/movies/cast";
	}

	/**
	 * Lista filmes com opções de busca e ordenação.
	 * Suporta filtros por nome, categoria, ator e ordenação por avaliação, nome ou data.
	 *
	 * @param orderBy Parâmetro de ordenação (rating, name, date).
	 * @param name Filtro por nome do filme.
	 * @param category Filtro por categoria.
	 * @param actor Filtro por nome do ator.
	 * @return ModelAndView com a lista de filmes filtrados e ordenados.
	 */
	@RequestMapping("/movies/list")
	public ModelAndView getMovies(
			@RequestParam(name="orderBy", required=false) String orderBy,
			@RequestParam(name="name", required=false) String name,
			@RequestParam(name="category", required=false) String category,
			@RequestParam(name="actor", required=false) String actor) {

		ModelAndView mav = new ModelAndView();

		List<Movie> moviesList = appService.findMovies();

		if (name != null) {
			moviesList = appService.findMoviesByName(name);
		}
		else if (category != null) {
			moviesList = appService.findMoviesByCategory(category);
		}
		else if (actor != null) {
			moviesList = appService.findMoviesByActor(actor);
		}
		else {
			moviesList = appService.findMovies();
		}

		if (orderBy != null) {
			if (orderBy.equals("rating")) {
				moviesList.sort(Comparator.comparing(Movie::getRating).reversed());
			}
			else if (orderBy.equals("name")) {
				moviesList.sort(Comparator.comparing(Movie::getName));
			}
			else if(orderBy.equals("date")) {
				moviesList.sort(Comparator.comparing(Movie::getDate).reversed());
			}
		}
		mav.addObject("movies", moviesList);
		mav.setViewName("index");
		return mav;
	}

	/**
	 * Exibe informações detalhadas de um filme específico, incluindo seu elenco.
	 *
	 * @param id ID do filme.
	 * @return ModelAndView com os detalhes do filme e elenco.
	 */
	@RequestMapping(method=RequestMethod.GET, value="/movies/{id}")
	public ModelAndView getMovieInfo(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();
		Movie movie = appService.findMovie(id);
		List<Actor> actors = new ArrayList<Actor>(movie.getCast());
		mav.addObject("movie", movie);
		mav.addObject("cast", actors);
		mav.setViewName("info");
		return mav;
	}
}
