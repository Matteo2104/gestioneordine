package it.prova.gestioneordine.service.categoria;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordine.dao.EntityManagerUtil;
import it.prova.gestioneordine.dao.categoria.CategoriaDAO;
import it.prova.gestioneordine.model.Articolo;
import it.prova.gestioneordine.model.Categoria;
import it.prova.gestioneordine.model.Ordine;

public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaDAO categoriaDAO;
	
	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;		
	}
	
	@Override
	public List<Categoria> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Categoria categoriaInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			categoriaDAO.setEntityManager(entityManager);
			
			categoriaDAO.insert(categoriaInstance);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException("errore nel service");
		}
	}

	@Override
	public void rimuovi(Categoria categoriaInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
}
