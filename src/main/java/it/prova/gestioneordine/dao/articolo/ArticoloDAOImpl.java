package it.prova.gestioneordine.dao.articolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordine.model.Articolo;

public class ArticoloDAOImpl implements ArticoloDAO {

	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Articolo> list() throws Exception {
		TypedQuery<Articolo> query = entityManager.createQuery("from Articolo", Articolo.class);
		return query.getResultList();
	}

	@Override
	public Articolo get(Long id) throws Exception {
		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Articolo o) throws Exception {
		if (o == null)
			throw new RuntimeException("Problema nei valori passati in input");
		
		entityManager.persist(o);
	}

	@Override
	public void delete(Articolo o) throws Exception {
		if (o == null)
			throw new RuntimeException("Problema nei valori passati in input");
		
		entityManager.remove(entityManager.merge(o));
	}
	
	@Override
	public Articolo findByIdFetchingCategorie(Long id) throws Exception {
		TypedQuery<Articolo> query = entityManager
				.createQuery("select a FROM Articolo a left join fetch a.categorie g where a.id = ?1", Articolo.class);
		query.setParameter(1, id);
		return query.getResultList().stream().findFirst().orElse(null);
	}
}
