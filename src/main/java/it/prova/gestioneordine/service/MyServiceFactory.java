package it.prova.gestioneordine.service;

import it.prova.gestioneordine.dao.MyDaoFactory;
import it.prova.gestioneordine.service.articolo.ArticoloService;
import it.prova.gestioneordine.service.articolo.ArticoloServiceImpl;
import it.prova.gestioneordine.service.categoria.CategoriaService;
import it.prova.gestioneordine.service.categoria.CategoriaServiceImpl;
import it.prova.gestioneordine.service.ordine.OrdineService;
import it.prova.gestioneordine.service.ordine.OrdineServiceImpl;

public class MyServiceFactory {

	private static OrdineService ordineServiceInstance = null;
	private static ArticoloService articoloServiceInstance = null;
	private static CategoriaService categoriaServiceInstance = null;

	public static OrdineService getOrdineServiceInstance() {
		if (ordineServiceInstance == null)
			ordineServiceInstance = new OrdineServiceImpl();

		ordineServiceInstance.setOrdineDAO(MyDaoFactory.getOrdineDAOInstance());

		return ordineServiceInstance;
	}

	public static ArticoloService getArticoloServiceInstance() {
		if (articoloServiceInstance == null)
			articoloServiceInstance = new ArticoloServiceImpl();

		articoloServiceInstance.setArticoloDAO(MyDaoFactory.getArticoloDAOInstance());
		articoloServiceInstance.setCategoriaDAO(MyDaoFactory.getCategoriaDAOInstance());

		return articoloServiceInstance;
	}
	
	public static CategoriaService getCategoriaServiceInstance() {
		if (categoriaServiceInstance == null)
			categoriaServiceInstance = new CategoriaServiceImpl();

		categoriaServiceInstance.setCategoriaDAO(MyDaoFactory.getCategoriaDAOInstance());

		return categoriaServiceInstance;
	}

}
