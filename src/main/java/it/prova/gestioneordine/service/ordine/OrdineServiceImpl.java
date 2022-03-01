package it.prova.gestioneordine.service.ordine;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordine.dao.ordine.OrdineDAO;
import it.prova.gestioneordine.model.Articolo;
import it.prova.gestioneordine.model.Ordine;
import it.prova.gestioneordine.dao.EntityManagerUtil;

public class OrdineServiceImpl implements OrdineService {

	private OrdineDAO ordineDAO;
	
	@Override
	public void setOrdineDAO(OrdineDAO ordineDAO) {
		this.ordineDAO = ordineDAO;
	}
	
	@Override
	public List<Ordine> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ordine caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			ordineDAO.setEntityManager(entityManager);
			
			ordineDAO.update(ordineInstance);
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException("errore nel service");
		}
	}

	@Override
	public void inserisciNuovo(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			
			entityManager.getTransaction().begin();
			
			ordineDAO.setEntityManager(entityManager);
			
			ordineDAO.insert(ordineInstance);
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException("errore nel service");
		}
	}

	@Override
	public void rimuovi(Ordine ordineInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiungiArticoloAdOrdine(Ordine ordineInstance, Articolo articoloInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ordineDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se risulta presente quel cd non deve essere inserito
			ordineInstance = entityManager.merge(ordineInstance);
			// attenzione che genereInstance deve essere già presente (lo verifica dall'id)
			// se così non è viene lanciata un'eccezione
			articoloInstance = entityManager.merge(articoloInstance);

			ordineInstance.getArticoli().add(articoloInstance);
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
	public Ordine caricaSingoloElementoEager(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			ordineDAO.setEntityManager(entityManager);
			
			return ordineDAO.getEager(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	

}
