package br.com.einecke.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.einecke.dao.impl.UserDAOImpl;
import br.com.einecke.entitiy.User;
import br.com.einecke.morphia.MorphiaService;
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
	 * Contrutor do rest, inicializa o MorphiaService e o dao, necessários para as transações.
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
	@Produces({ "application/json", "text/plain" })
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/login")
	public String login(@FormParam("login") String login, @FormParam("password") String password) {
		String userPassword = dao.getUserPassword(login);

		if (password.equals(userPassword)) {
			return "password successfully";
		}
		return "Password incorrect";

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
	 * Cadatra dados para testes.
	 * 
	 * @return ...
	 */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrarTeste")
	public String dataTest() { 
		dao.addUsersTest();
		return "Task cadastrada com sucesso!";
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
		return dao.getAllUsers(); // TODO Está funcionando mas esta dando uma excessão, verificar.

	}

}
