package it.prova.gestioneordine.test;

import it.prova.gestioneordine.dao.EntityManagerUtil;
import it.prova.gestioneordine.model.Ordine;
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

			testInserimentoNuovoOrdine(ordineServiceInstance);
			
			testAggiornmentoOrdine(ordineServiceInstance);

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}
	
	private static void testInserimentoNuovoOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testInserimentoNuovoOrdine ..........");
		
		Ordine ordine = new Ordine("matteo scarcella", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine);
		if (ordine.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		System.out.println(".......... FINE testInserimentoNuovoOrdine: successo ..........");
	}
	
	private static void testAggiornmentoOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testAggiornmentoOrdine ..........");
		
		Ordine ordine = new Ordine("matteo scarcella", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine);
		if (ordine.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		ordine.setIndirizzoSpedizione("via dell'aquila reale");
		ordineServiceInstance.aggiorna(ordine);
		if (!ordine.getIndirizzoSpedizione().equals("via dell'aquila reale"))
			throw new RuntimeException("non è stato possibile aggiornare il record"); 
		
		System.out.println(".......... FINE testAggiornmentoOrdine: successo ..........");
	}
}
