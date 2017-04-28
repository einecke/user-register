package br.com.einecke.dao;

import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import br.com.einecke.entitiy.User;
/**
 * Interface de acesso a dados
 * 
 * @author andre.pandini
 *
 */
public interface UserDAO extends DAO<User, ObjectId> {

	/**
	 * Lista todos os usuários.
	 * 
	 * @return Lista de usuarios.
	 */
	public List<User> getAllUsers();
	
	/**
	 * Retorna a senha do usuários informado;
	 * 
	 * @param userId Username.
	 * 
	 * @return Senha.
	 */
	public String getUserPassword(String userId);
	
	
	/**
	 * Retorna o User(objeto) com base no userId informado.
	 * 
	 * @param userId - Nome de usuário.
	 * 
	 * @return Objeto do tipo User
	 */
	public User getById(String userId);

	/**
	 * Adicionar um usuário.
	 * 
	 * @param user - dados do usuário a ser cadastrado (Formato JSON)
	 */
	void addUser(User user);

	/**
	 * Deleta um usuário.
	 * 
	 * @param userId - ID do usuário
	 */
	void delete(String userId);

	
	/**
	 * Edita um usuário.
	 * 
	 * @param user - Objeto usuário, já editado
	 */
	void editUser(User user);

}