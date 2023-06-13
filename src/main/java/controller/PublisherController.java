package controller;

import java.util.List;

import dao.PublisherDAO;
import model.Publisher;

public class PublisherController {

	private PublisherDAO publisherDAO;

	public PublisherController(PublisherDAO publisherDAO) {
		this.publisherDAO = publisherDAO;
	}

	public void savePublisher(Publisher publisher) {
		publisherDAO.savePublisher(publisher);
	}

	public void updatePublisher(Publisher publisher) {
		publisherDAO.updatePublisher(publisher);
	}

	public void deletePublisher(Publisher publisher) {
		publisherDAO.deletePublisher(publisher);
	}

	public Publisher getPublisherById(Long id) {
		return publisherDAO.getPublisherById(id);
	}

	public List<Publisher> getAllPublishers() {
		return publisherDAO.getAllPublishers();
	}
}
