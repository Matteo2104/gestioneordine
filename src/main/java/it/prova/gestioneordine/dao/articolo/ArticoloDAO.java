package it.prova.gestioneordine.dao.articolo;

import it.prova.gestioneordine.dao.IBaseDAO;
import it.prova.gestioneordine.model.Articolo;

public interface ArticoloDAO extends IBaseDAO<Articolo> {

	public Articolo findByIdFetchingCategorie(Long id) throws Exception;

}
