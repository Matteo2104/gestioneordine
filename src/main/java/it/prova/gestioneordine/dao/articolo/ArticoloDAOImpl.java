package it.prova.gestioneordine.dao.articolo;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordine.model.Articolo;

public class ArticoloDAOImpl implements ArticoloDAO {

	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Articolo> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Articolo get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Articolo o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Articolo o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Articolo o) throws Exception {
		// TODO Auto-generated method stub
		
	}
}