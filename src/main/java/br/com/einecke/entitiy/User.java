package br.com.einecke.entitiy;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("user")
public class User {

	@Id
	private ObjectId objectId;
	
	private String userId;

	private String firstName;

	private String lastName;

	private boolean isAdmin;
	
	private String password;

	public User() {}

	/**
	 * Contrutor do objeto usuário.
	 * 
	 * @param userId - Nome de usuário;
	 * @param firstName - Primeiro nome;
	 * @param lastName - Sobrenome;
	 * @param isAdmin - Usuário é administrador?
	 * @param password - Senha do usuario;
	 */
	public User(String userId, String firstName, String lastName, boolean isAdmin, String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
		this.password = password;
	}

	public ObjectId getObjectId() {
		return objectId;
	}

	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
