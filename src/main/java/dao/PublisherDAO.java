package dao;

import java.util.List;

import model.Publisher;

public interface PublisherDAO {
	
	void savePublisher(Publisher publisher);

	void updatePublisher(Publisher publisher);

	void deletePublisher(Publisher publisher);

	Publisher getPublisherById(Long id);

	List<Publisher> getAllPublishers();
}