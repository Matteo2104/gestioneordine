package it.prova.gestioneordine.test;

import it.prova.gestioneordine.dao.EntityManagerUtil;
import it.prova.gestioneordine.model.Articolo;
import it.prova.gestioneordine.model.Categoria;
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
			
			//testInserimentoArticoloAdOrdine(ordineServiceInstance, articoloServiceInstance);
			
			testInserimentoCategoriaAdArticolo(articoloServiceInstance, categoriaServiceInstance);

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
	
	private static void testInserimentoArticoloAdOrdine(OrdineService ordineServiceInstance, ArticoloService articoloServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testInserimentoArticoloAdOrdine ..........");
		
		// inserisco un ordine 
		Ordine ordine = new Ordine("matteo scarcella", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine);
		if (ordine.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// inserisco un articolo all'ordine
		Articolo articolo = new Articolo("giornale", 1);
		
		//prima devo aggiungere al db l'articolo
		articoloServiceInstance.inserisciNuovo(articolo);
		if (articolo.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine, articolo);
		
		// ricarico l'ordine eager in modo da forzare il test
		Ordine ordineReloaded = ordineServiceInstance.caricaSingoloElementoEager(ordine.getId());
		if (ordineReloaded.getArticoli() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		System.out.println(".......... FINE testInserimentoArticoloAdOrdine: successo ..........");
	}
	
	private static void testInserimentoCategoriaAdArticolo(ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testInserimentoCategoriaAdArticolo ..........");
		
		// inserisco un articolo 
		Articolo articolo = new Articolo("giornale", 1);
		
		//aggiungo l'articolo al db
		articoloServiceInstance.inserisciNuovo(articolo);
		if (articolo.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// aggiungo una categoria
		Categoria categoria = new Categoria("thriller", "00a1");
		
		//aggiungo la categoria al db
		categoriaServiceInstance.inserisciNuovo(categoria);
		if (categoria.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// eseguo il collegamento
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo, categoria);
		
		
		// ricarico l'articolo eager in modo da forzare il test
		Articolo articoloReloaded = articoloServiceInstance.caricaSingoloElementoEager(articolo.getId());
		if (articoloReloaded.getCategorie().isEmpty())
			throw new RuntimeException("non è stato possibile inserire il record");
		
		
		System.out.println(".......... FINE testInserimentoCategoriaAdArticolo: successo ..........");
	}
}
