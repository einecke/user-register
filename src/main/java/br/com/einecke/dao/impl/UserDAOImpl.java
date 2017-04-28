package br.com.einecke.dao.impl;

import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
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
	public void editUser(User user) {
		Query<User> query = createQuery().field("userId").equal(user.getUserId());

		UpdateOperations<User> upd = createUpdateOperations().disableValidation().set("userId", user.getUserId()).set("firstName", user.getFirstName())
				.set("lastName", user.getLastName()).set("isAdmin", user.isAdmin()).set("password", user.getPassword());

		this.update(query, upd);
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

	@Override
	public void delete(String userId) {
		Query<User> query = createQuery().field("userId").equal(userId);
		User user = query.get();
		this.delete(user);
	}

}