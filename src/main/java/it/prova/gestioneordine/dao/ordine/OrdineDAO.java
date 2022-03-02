package it.prova.gestioneordine.dao.ordine;

import java.util.List;

import it.prova.gestioneordine.dao.IBaseDAO;
import it.prova.gestioneordine.model.Categoria;
import it.prova.gestioneordine.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {

	public Ordine findByIdFetchingArticoli(Long id) throws Exception;

	// esercizi per casa
	public List<Ordine> findOrdineByCategoria(Categoria categoria) throws Exception;

	public Ordine mostRecentOrdineDataCategoria(Categoria categoria) throws Exception;

	List<String> findAllindirizziDiOrdiniConSerialeInArticolo(String pezzoDiSeriale) throws Exception;
}
