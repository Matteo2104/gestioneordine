package it.prova.gestioneordine.test;

import it.prova.gestioneordine.dao.EntityManagerUtil;
import it.prova.gestioneordine.service.MyServiceFactory;
import it.prova.gestioneordine.service.articolo.ArticoloService;
import it.prova.gestioneordine.service.categoria.CategoriaService;
import it.prova.gestioneordine.service.ordine.OrdineService;

public class TestGestioneOrdine {
	public static void main(String[] args) {
		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();

		try {

			

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}
}
