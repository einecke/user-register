package br.com.einecke.dao.impl;

import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import br.com.einecke.dao.UserDAO;
import br.com.einecke.entitiy.User;

/**
 * Implementação do userDAO.
 *
 */
public class UserDAOImpl extends BasicDAO<User, ObjectId> implements UserDAO {

	public UserDAOImpl(Class<User> entityClass, Datastore ds) {
		super(entityClass, ds);
	}

	@Override
	public User getByFirstNameLastName(String firstName, String lastName) {
		Query<User> query = createQuery().field("firstName").equal(firstName).field("lastName").equal(lastName);
		return query.get();
	}

	@Override
	public List<User> getByFirstName(String firstName) {
		Query<User> query = createQuery().field("firstName").equal(firstName);
		return query.asList();
	}

	@Override
	public List<User> getAllUsers() {
		Query<User> query = createQuery();
		return query.asList();
	}

	@Override
	public String getUserPassword(String userId) {
		Query<User> query = createQuery().field("userId").equal(userId);
		User user = query.get();
		return user.getPassword();
	}

	@Override
	public void addUsersTest() {
		User user1 = new User("andre", "André", "Einecke", true, "123456");
		User user2 = new User("daniel.dc", "Daniel", "", true, "54321");

		this.save(user1);
		this.save(user2);
	}

	@Override
	public void addUser(User user) {
		this.save(user);
	}

	@Override
	public User getById(String userId) {
		Query<User> query = createQuery().field("userId").equal(userId);
		return query.get();
	}

}