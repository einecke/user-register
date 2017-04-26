package br.com.einecke.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

/**
 * Serviço do Morphia para a conexão com o MongoDB.
 * 
 * @author andre.pandini
 */
public class MorphiaService {

	private Morphia morphia;
	private Datastore datastore;

	/**
	 * Utilizando o endereço padrão do MongoDB: 127.0.0.1:27017;
	 * Cria um banco chamado "user_database".
	 */
	public MorphiaService() {
		MongoClient mongoClient = new MongoClient("127.0.0.1:27017");
		this.morphia = new Morphia();
		String databaseName = "user_database";
		this.datastore = morphia.createDatastore(mongoClient, databaseName);
	}

	public Morphia getMorphia() {
		return morphia;
	}

	public void setMorphia(Morphia morphia) {
		this.morphia = morphia;
	}

	public Datastore getDatastore() {
		return datastore;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}
}
