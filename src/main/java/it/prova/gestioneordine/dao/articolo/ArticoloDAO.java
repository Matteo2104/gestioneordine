package it.prova.gestioneordine.dao.articolo;

import it.prova.gestioneordine.dao.IBaseDAO;
import it.prova.gestioneordine.model.Articolo;
import it.prova.gestioneordine.model.Categoria;

public interface ArticoloDAO extends IBaseDAO<Articolo> {

	public Articolo findByIdFetchingCategorie(Long id) throws Exception;

	Long sumOfArticoliPerCategoria(Categoria categoria) throws Exception;

}
