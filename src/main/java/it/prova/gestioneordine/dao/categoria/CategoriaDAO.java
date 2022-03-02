package it.prova.gestioneordine.dao.categoria;

import java.util.List;

import it.prova.gestioneordine.dao.IBaseDAO;
import it.prova.gestioneordine.model.Categoria;
import it.prova.gestioneordine.model.Ordine;

public interface CategoriaDAO extends IBaseDAO<Categoria> {

	public Categoria findByIdFetchingArticoli(Long id) throws Exception;

	List<Categoria> findAllDistinctCategorieDatoOrdine(Ordine ordine) throws Exception;

	List<String> findAllCodiciCategorieOrdiniFebbraio2022() throws Exception;

}
