package chapter06;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class BookEJB implements BookEJBRemote {

	@PersistenceContext(unitName = "chapter06PU")
	private EntityManager em;

	@Override
	public List<Book> findBooks() {
		TypedQuery<Book> query = em.createNamedQuery("findAllBooks", Book.class);
		return query.getResultList();
	}

	@Override
	public Book findBookById(Long id) {
		return em.find(Book.class, id);
	}

	@Override
	public Book createBook(Book book) {
		System.out.println("Buch wird erzeugt!");
		em.persist(book);
		return book;
	}

	@Override
	public void deleteBook(Book book) {
		em.remove(em.merge(book));
	}

	@Override
	public Book updateBook(Book book) {
		return em.merge(book);
	}

}
