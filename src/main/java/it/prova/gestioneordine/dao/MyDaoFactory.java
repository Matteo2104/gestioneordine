package it.prova.gestioneordine.dao;

import it.prova.gestioneordine.dao.articolo.ArticoloDAO;
import it.prova.gestioneordine.dao.articolo.ArticoloDAOImpl;
import it.prova.gestioneordine.dao.categoria.CategoriaDAO;
import it.prova.gestioneordine.dao.categoria.CategoriaDAOImpl;
import it.prova.gestioneordine.dao.ordine.OrdineDAO;
import it.prova.gestioneordine.dao.ordine.OrdineDAOImpl;

public class MyDaoFactory {

	private static OrdineDAO ordineDaoInstance = null;
	private static ArticoloDAO articoloDaoInstance = null;
	private static CategoriaDAO categoriaDaoInstance = null;

	public static OrdineDAO getOrdineDAOInstance() {
		if (ordineDaoInstance == null)
			ordineDaoInstance = new OrdineDAOImpl();

		return ordineDaoInstance;
	}

	public static ArticoloDAO getArticoloDAOInstance() {
		if (articoloDaoInstance == null)
			articoloDaoInstance = new ArticoloDAOImpl();

		return articoloDaoInstance;
	}
	
	public static CategoriaDAO getCategoriaDAOInstance() {
		if (categoriaDaoInstance == null)
			categoriaDaoInstance = new CategoriaDAOImpl();

		return categoriaDaoInstance;
	}

}
