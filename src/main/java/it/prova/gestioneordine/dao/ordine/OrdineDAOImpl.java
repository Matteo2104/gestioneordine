package it.prova.gestioneordine.dao.ordine;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordine.model.Articolo;
import it.prova.gestioneordine.model.Categoria;
import it.prova.gestioneordine.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {
	
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Ordine> list() throws Exception {
		TypedQuery<Ordine> query = entityManager.createQuery("from Ordine", Ordine.class);
		return query.getResultList();
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
		if (o == null)
			throw new RuntimeException("Problema nel valore passato in input");

		entityManager.remove(entityManager.merge(o));
	}
	
	@Override
	public Ordine findByIdFetchingArticoli(Long id) throws Exception {
		TypedQuery<Ordine> query = entityManager
				.createQuery("select o FROM Ordine o left join fetch o.articoli a where o.id = ?1", Ordine.class);
		query.setParameter(1, id);
		return query.getResultList().stream().findFirst().orElse(null);
	}
	
	@Override
	public List<Ordine> findOrdineByCategoria(Categoria categoria) throws Exception {
		//TypedQuery<Ordine> query = entityManager.createQuery("select o FROM Ordine o join fetch o.articoli a where a.id = ?1", Ordine.class);
		TypedQuery<Ordine> query = entityManager.createQuery("select a.ordine from Articolo a join a.categorie c where c.id = ?1", Ordine.class);
		
		query.setParameter(1, categoria.getId());
		return query.getResultList();
	}
	
	@Override
	public Ordine mostRecentOrdineDataCategoria(Categoria categoria) throws Exception {
		TypedQuery<Ordine> query = entityManager.createQuery("select a.ordine from Articolo a join a.categorie c where c.id = ?1 order by a.ordine.dataSpedizione asc", Ordine.class);
		
		query.setParameter(1, categoria.getId());
		List<Ordine> tuttiOrdini = query.getResultList();
		return tuttiOrdini.get(0);
	}
	
	@Override
	public List<String> findAllindirizziDiOrdiniConSerialeInArticolo(String pezzoDiSeriale) throws Exception {
		TypedQuery<String> query = entityManager.createQuery("select distinct o.indirizzoSpedizione from Ordine o join o.articoli a where a.numeroSeriale like ?1", String.class);
		
		query.setParameter(1, "%" + pezzoDiSeriale + "%");
		return query.getResultList();
	}

}
