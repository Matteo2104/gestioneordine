package it.prova.gestioneordine.dao.ordine;

import it.prova.gestioneordine.dao.IBaseDAO;
import it.prova.gestioneordine.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {

	public Ordine findByIdFetchingArticoli(Long id) throws Exception;
}
