package it.prova.gestioneordine.dao.categoria;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordine.model.Articolo;
import it.prova.gestioneordine.model.Categoria;
import it.prova.gestioneordine.model.Ordine;

public class CategoriaDAOImpl implements CategoriaDAO {

	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Categoria> list() throws Exception {
		TypedQuery<Categoria> query = entityManager.createQuery("from Categoria", Categoria.class);
		return query.getResultList();
	}

	@Override
	public Categoria get(Long id) throws Exception {
		return entityManager.find(Categoria.class, id);
	}

	@Override
	public void update(Categoria o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Categoria o) throws Exception {
		if (o == null)
			throw new RuntimeException("Problema nei valori passati in input");
		
		entityManager.persist(o);
	}

	@Override
	public void delete(Categoria o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Categoria findByIdFetchingArticoli(Long id) throws Exception {
		TypedQuery<Categoria> query = entityManager
				.createQuery("select c FROM Categoria c left join fetch c.articoli a where c.id = ?1", Categoria.class);
		query.setParameter(1, id);
		return query.getResultList().stream().findFirst().orElse(null);
	}
	
	@Override
	public List<Categoria> findAllDistinctCategorieDatoOrdine(Ordine ordine) throws Exception {
		TypedQuery<Categoria> query = entityManager.createQuery("select distinct c FROM Categoria c join c.articoli a where a.ordine.id = ?1", Categoria.class);
		query.setParameter(1, ordine.getId());
		return query.getResultList();
	}

	@Override 
	public List<String> findAllCodiciCategorieOrdiniFebbraio2022() throws Exception {
		Date dataInizio = new Date();
		Date dataFine = new Date();
		Calendar c = Calendar.getInstance();
		c.set(2022, 1, 0); // febbraio 2022
		dataInizio = c.getTime();
		c.set(2022, 2, 0); // marzo 2022
		dataFine = c.getTime();
		
		TypedQuery<String> query = entityManager.createQuery("select distinct c.codice FROM Categoria c join c.articoli a where a.ordine.dataSpedizione between ?1 and ?2", String.class);
		query.setParameter(1, dataInizio);
		query.setParameter(2, dataFine);
		return query.getResultList();
	}
}
