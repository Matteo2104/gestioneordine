package it.prova.gestioneordine.service.articolo;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordine.dao.EntityManagerUtil;
import it.prova.gestioneordine.dao.articolo.ArticoloDAO;
import it.prova.gestioneordine.model.Articolo;
import it.prova.gestioneordine.model.Categoria;
import it.prova.gestioneordine.model.Ordine;

public class ArticoloServiceImpl  implements ArticoloService {

	private ArticoloDAO articoloDAO;
	
	@Override
	public void setArticoloDAO(ArticoloDAO articoloDAO) {
		this.articoloDAO = articoloDAO;
	}
	
	@Override
	public List<Articolo> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Articolo caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Articolo articoloInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			articoloDAO.setEntityManager(entityManager);
			
			articoloDAO.insert(articoloInstance);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException("errore nel service");
		}
	}

	@Override
	public void rimuovi(Articolo articoloInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiungiCategoriaAdArticolo(Articolo articoloInstance, Categoria categoriaInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se risulta presente quel cd non deve essere inserito
			articoloInstance = entityManager.merge(articoloInstance);
			// attenzione che genereInstance deve essere già presente (lo verifica dall'id)
			// se così non è viene lanciata un'eccezione
			categoriaInstance = entityManager.merge(categoriaInstance);

			articoloInstance.getCategorie().add(categoriaInstance);
			// l'update non viene richiamato a mano in quanto
			// risulta automatico, infatti il contesto di persistenza
			// rileva che cdInstance ora è dirty vale a dire che una sua
			// proprieta ha subito una modifica (vale anche per i Set ovviamente)
			// inoltre se risultano già collegati lo capisce automaticamente grazie agli id

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Articolo caricaSingoloElementoEager(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			articoloDAO.setEntityManager(entityManager);
			
			return articoloDAO.findByIdFetchingCategorie(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
}
