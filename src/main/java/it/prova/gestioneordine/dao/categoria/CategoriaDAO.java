package it.prova.gestioneordine.dao.categoria;

import it.prova.gestioneordine.dao.IBaseDAO;
import it.prova.gestioneordine.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria> {

	public Categoria findByIdFetchingArticoli(Long id) throws Exception;

}
