package br.com.einecke.controller;

import java.util.List;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import br.com.einecke.dao.impl.UserDAOImpl;
import br.com.einecke.entitiy.User;
import br.com.einecke.morphia.MorphiaService;
import br.com.einecke.util.Login;

/**
 * 
 * WebService rest para cadastro de usuários.
 * 
 * @author andre.pandini
 *
 */
@Path("/service")
public class UserController {
	private MorphiaService morphiaService;
	private final UserDAOImpl dao;

	/**
	 * Contrutor do rest, inicializa o MorphiaService e o dao, necessários para
	 * as transações.
	 */
	public UserController() {
		morphiaService = new MorphiaService();
		dao = new UserDAOImpl(User.class, morphiaService.getDatastore());
	}

	/**
	 * Valida o login
	 * 
	 * @param login
	 *            - Login;
	 * @param password
	 *            - Senha.
	 * @return Resultado do chamado.
	 */
	@POST
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/login")
	public String login(Login login) {
		String userPassword = dao.getUserPassword(login.getUserId());
		if (login.getPassword().equals(userPassword)) {
			return "password successfully";
		}
		return "Password incorrect";
	}

	/**
	 * Exclui taks pelo código
	 * 
	 * @param userId
	 *            - Código do registro que será excluido.
	 * @return Resultado do chamado.
	 */
	@POST
	@Produces("application/json; charset=UTF-8")
	@Consumes("text/plain; charset=UTF-8")
	@Path("/excluir")
	public String excluir(String userId) {
		try {
			dao.delete(userId);
			return "usuário excluido com sucesso!";
		} catch (Exception e) {
			return "Erro ao excluir usuário. " + e.getMessage();
		}
	}

	/**
	 * Cadatra um usuário.
	 * 
	 * @param user
	 *            - dados do usuário.
	 * 
	 * @return Resultado da operação.
	 */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
	public String cadastrar(User user) {
		try {
			dao.addUser(user);
			return "Usuario cadastrado com sucesso";
		} catch (Exception e) { // NOSONAR
			return "Erro ao cadastrar Usuario " + e.getMessage();
		}
	}

	/**
	 * Cadatra um usuário.
	 * 
	 * @param user
	 *            - dados do usuário.
	 * 
	 * @return Resultado da operação.
	 */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/editar")
	public String edit(User user) {
		try {
			dao.editUser(user);
			return "Usuario editado com sucesso";
		} catch (Exception e) { // NOSONAR
			return "Erro ao editar Usuario " + e.getMessage();
		}
	}

	/**
	 * Retorna um JSON com todos os registros.
	 * 
	 * @return Todos os usuários cadastrados, no formato JSON.
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/todas")
	public List<User> list() {
		return dao.getAllUsers();
	}

	/**
	 * Busca o usuário pelo código
	 * 
	 * @param userId
	 *            - Código do usuário;
	 * @return Usuário.
	 */
	@POST
	@Produces("application/json; charset=UTF-8")
	@Consumes("text/plain; charset=UTF-8")
	@Path("/getUser")
	public User getUse(String userId) {
		return dao.getById(userId);
	}
}
