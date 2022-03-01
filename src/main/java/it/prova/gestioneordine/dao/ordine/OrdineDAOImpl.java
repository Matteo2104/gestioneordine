package it.prova.gestioneordine.dao.ordine;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordine.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {
	
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Ordine> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ordine get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Ordine o) throws Exception {
		if (o == null)
			throw new RuntimeException("Problema nel valore passato in input");
		
		o = entityManager.merge(o);
	}

	@Override
	public void insert(Ordine o) throws Exception {
		if (o == null)
			throw new RuntimeException("Problema nel valore passato in input");
		
		entityManager.persist(o);
	}

	@Override
	public void delete(Ordine o) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Ordine getEager(Long id) throws Exception {
		return entityManager.find(Ordine.class, id);
	}

}
