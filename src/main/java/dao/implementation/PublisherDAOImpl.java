package dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dao.PublisherDAO;
import model.Publisher;

public class PublisherDAOImpl implements PublisherDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public PublisherDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void savePublisher(Publisher publisher) {
		entityManager.persist(publisher);
	}

	@Override
	public void updatePublisher(Publisher publisher) {
		entityManager.merge(publisher);
	}

	@Override
	public void deletePublisher(Publisher publisher) {
		entityManager.remove(publisher);
	}

	@Override
	public Publisher getPublisherById(Long id) {
		return entityManager.find(Publisher.class, id);
	}

	@Override
	public List<Publisher> getAllPublishers() {
		TypedQuery<Publisher> query = entityManager.createQuery("SELECT p FROM Publisher p", Publisher.class);
		return query.getResultList();
	}

}
