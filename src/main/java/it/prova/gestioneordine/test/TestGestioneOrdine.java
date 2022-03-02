package it.prova.gestioneordine.test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.prova.gestioneordine.dao.EntityManagerUtil;
import it.prova.gestioneordine.model.Articolo;
import it.prova.gestioneordine.model.Categoria;
import it.prova.gestioneordine.model.Ordine;
import it.prova.gestioneordine.service.MyServiceFactory;
import it.prova.gestioneordine.service.articolo.ArticoloService;
import it.prova.gestioneordine.service.categoria.CategoriaService;
import it.prova.gestioneordine.service.ordine.OrdineService;
import it.prova.gestioneordine.service.ordine.OrdineServiceImpl;

public class TestGestioneOrdine {
	public static void main(String[] args) {
		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();

		try {
			
			// funziona
			//testInserimentoNuovoOrdine(ordineServiceInstance);
			
			// funziona
			//testAggiornmentoOrdine(ordineServiceInstance);
			
			// funziona
			//testInserimentoArticoloAdOrdine(ordineServiceInstance, articoloServiceInstance);
			
			// funziona
			//testInserimentoCategoriaAdArticolo(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			// funziona
			//testInserimentoArticoloACategoria(categoriaServiceInstance, articoloServiceInstance, ordineServiceInstance);
			
			// funziona
			//testRimozioneArticoloConCategoria(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			
			//testRimozioneOrdineConArticoli(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			// funziona
			//testRimozioneCategoriaDaArticolo(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			// funziona
			//testRimozioneArticoloDaCategoria(categoriaServiceInstance, articoloServiceInstance, ordineServiceInstance);
			
			// funziona
			//testList(articoloServiceInstance, ordineServiceInstance, categoriaServiceInstance);
			
			// ok
			testTrovaOrdiniDataCategoria(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			// ok
			testTrovaCategorieDatiOrdini(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			// ok
			testSommaArticoliDatoOrdine(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			//testOrdinePiuRecente(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			// ok
			testCodiciCategoriaOrdiniFebbraio2022(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			// ok
			testSommaPrezziArticoliOrdineMarioRossi(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			// ok
			testTrovaIndirizziOrdiniConNumeroSeriale(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			

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
		Ordine ordine = new Ordine("mario verdi", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine);
		if (ordine.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// inserisco un articolo
		Articolo articolo = new Articolo("latte", 2);
		articolo.setOrdine(ordine);
		articoloServiceInstance.inserisciNuovo(articolo);
		if (articolo.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine, articolo);
		
		// ricarico l'ordine eager in modo da forzare il test
		Ordine ordineReloaded = ordineServiceInstance.caricaSingoloElementoEager(ordine.getId());
		if (ordineReloaded.getArticoli().isEmpty())
			throw new RuntimeException("non è stato possibile collegare i record");
		
		System.out.println(".......... FINE testInserimentoArticoloAdOrdine: successo ..........");
	}
	
	private static void testInserimentoCategoriaAdArticolo(ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testInserimentoCategoriaAdArticolo ..........");
		
		// inserisco un ordine
		Ordine ordine = new Ordine("mario verdi", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine);
		if (ordine.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
				
				
		// inserisco un articolo 
		Articolo articolo = new Articolo("giornale", 1);
		articolo.setOrdine(ordine);
		
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
	
	private static void testInserimentoArticoloACategoria(CategoriaService categoriaServiceInstance, ArticoloService articoloServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testInserimentoArticoloACategoria ..........");
		
		// inserisco un ordine
		Ordine ordine = new Ordine("mario verdi", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine);
		if (ordine.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// inserisco un articolo
		Articolo articolo = new Articolo("giornale", 1);
		articolo.setOrdine(ordine);
		articoloServiceInstance.inserisciNuovo(articolo);
		if (articolo.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
				
		// aggiungo una categoria
		Categoria categoria = new Categoria("thriller", "00a1");
		categoriaServiceInstance.inserisciNuovo(categoria);
		if (categoria.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
	
		// eseguo il collegamento
		categoriaServiceInstance.aggiungiArticoloACategoria(categoria, articolo);
		
		// ricarico la categoria eager in modo da forzare il test
		Categoria categoriaReloaded = categoriaServiceInstance.caricaSingoloElementoEager(categoria.getId());
		if (categoriaReloaded.getArticoli().isEmpty())
			throw new RuntimeException("non è stato possibile eseguire il collegamento tra i record");
		
		
		System.out.println(".......... FINE testInserimentoArticoloACategoria: successo ..........");
	}
	
	private static void testRimozioneArticoloConCategoria(ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testRimozioneArticoloConCategoria ..........");
		
		// inserisco un ordine
		Ordine ordine = new Ordine("mario verdi", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine);
		if (ordine.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
				
				
		// inserisco un articolo 
		Articolo articolo = new Articolo("giornale", 1);
		articolo.setOrdine(ordine);
		
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
		
		// a questo punto voglio rimuovere l'articolo, ma dev'essere impossibile
		try {
			articoloServiceInstance.rimuovi(articolo);
			throw new RuntimeException("il test è fallito");
		} catch (Exception e) {
			
		}
		
		System.out.println(".......... FINE testRimozioneArticoloConCategoria: successo ..........");
	}
	
	private static void testRimozioneCategoriaDaArticolo(ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testRimozioneCategoriaDaArticolo ..........");
		
		// inserisco un ordine
		Ordine ordine = new Ordine("mario verdi", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine);
		if (ordine.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
				
				
		// inserisco un articolo 
		Articolo articolo = new Articolo("giornale", 1);
		articolo.setOrdine(ordine);
		articoloServiceInstance.inserisciNuovo(articolo);
		if (articolo.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// aggiungo una categoria
		Categoria categoria = new Categoria("thriller", "00a1");
		categoriaServiceInstance.inserisciNuovo(categoria);
		if (categoria.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// eseguo il collegamento
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo, categoria);
		
		
		// ricarico l'articolo eager in modo da forzare il test
		Articolo articoloReloaded = articoloServiceInstance.caricaSingoloElementoEager(articolo.getId());
		if (articoloReloaded.getCategorie().isEmpty())
			throw new RuntimeException("non è stato possibile inserire il record");
		
		//System.out.println(articoloReloaded);
		
		// a questo punto voglio scollegare la categoria dall'articolo
		articoloServiceInstance.rimuoviCategoria(articoloReloaded, categoria);
		
		articoloReloaded = articoloServiceInstance.caricaSingoloElementoEager(articolo.getId());
		if (!articoloReloaded.getCategorie().isEmpty())
			throw new RuntimeException("non è stato possibile scollegare il record");
		
		
		System.out.println(".......... FINE testRimozioneCategoriaDaArticolo: successo ..........");
	}
	
	private static void testRimozioneArticoloDaCategoria(CategoriaService categoriaServiceInstance, ArticoloService articoloServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testRimozioneArticoloDaCategoria ..........");
		
		// inserisco un ordine
		Ordine ordine = new Ordine("mario verdi", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine);
		if (ordine.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
				
		// inserisco un articolo 
		Articolo articolo = new Articolo("giornale", 1);
		articolo.setOrdine(ordine);
		articoloServiceInstance.inserisciNuovo(articolo);
		if (articolo.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// aggiungo una categoria
		Categoria categoria = new Categoria("thriller", "00a1");
		categoriaServiceInstance.inserisciNuovo(categoria);
		if (categoria.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// eseguo il collegamento
		categoriaServiceInstance.aggiungiArticoloACategoria(categoria, articolo);
		
		// ricarico la categoria eager in modo da forzare il test
		Categoria categoriaReloaded = categoriaServiceInstance.caricaSingoloElementoEager(categoria.getId());
		if (categoriaReloaded.getArticoli().isEmpty())
			throw new RuntimeException("non è stato possibile eseguire il collegamento tra i record");
		
		// a questo punto voglio scollegare l'articolo dalla categoria
		categoriaServiceInstance.rimuoviArticolo(categoria, articolo);
		
		if (!categoria.getArticoli().isEmpty())
			throw new RuntimeException("non è stato possibile scollegare il record");
		
		System.out.println(".......... FINE testRimozioneArticoloDaCategoria: successo ..........");
	}
	
	private static void testList(ArticoloService articoloServiceInstance, OrdineService ordineServiceInstance, CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testList ..........");
		
		// inserisco un ordine
		Ordine ordine = new Ordine("mario verdi", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine);
		if (ordine.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// inserisco un articolo
		Articolo articolo = new Articolo("giornale", 1);
		articolo.setOrdine(ordine); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo);
		if (articolo.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// aggiungo una categoria
		Categoria categoria = new Categoria("thriller", "00a1");
		categoriaServiceInstance.inserisciNuovo(categoria);
		if (categoria.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		
		// adesso voglio stamparli tutti
		List<Ordine> listaDiOrdini = ordineServiceInstance.listAll();
		if (listaDiOrdini.size() < 1) 
			throw new RuntimeException("non è stato possibile stampare correttamente i record");
		
		List<Articolo> listaDiArticoli = articoloServiceInstance.listAll();
		if (listaDiArticoli.size() < 1) 
			throw new RuntimeException("non è stato possibile stampare correttamente i record");
		
		List<Categoria> listaDiCategorie = categoriaServiceInstance.listAll();
		if (listaDiArticoli.size() < 1) 
			throw new RuntimeException("non è stato possibile stampare correttamente i record");
		
		System.out.println(".......... FINE testList: successo ..........");
	}
	
	private static void testRimozioneOrdineConArticoli(ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testRimozioneOrdineConArticoli ..........");
		
		// inserisco un ordine
		Ordine ordine = new Ordine("mario verdi", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine);
		if (ordine.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// inserisco un articolo
		Articolo articolo = new Articolo("giornale", 1);
		articolo.setOrdine(ordine); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo);
		if (articolo.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// provo ad eliminare l'ordine
		
		
		System.out.println(".......... FINE testRimozioneOrdineConArticoli: successo ..........");
	}
	
	private static void testTrovaOrdiniDataCategoria(ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testTrovaOrdiniDataCategoria ..........");
		
		// inserisco 3 ordini
		Ordine ordine1 = new Ordine("mario verdi", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine1);
		if (ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Ordine ordine2 = new Ordine("matteo scarcella", "via walter tobagi");
		ordineServiceInstance.inserisciNuovo(ordine2);
		if (ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Ordine ordine3 = new Ordine("maria rossi", "via armando mecali");
		ordineServiceInstance.inserisciNuovo(ordine3);
		if (ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// inserisco articoli per ogni ordine
		Articolo articolo1ordine1 = new Articolo("giornale", 1);
		articolo1ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine1);
		if (articolo1ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine1 = new Articolo("giornale", 1);
		articolo2ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine1);
		if (articolo2ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine2 = new Articolo("giornale", 1);
		articolo1ordine2.setOrdine(ordine2); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine2);
		if (articolo1ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine3 = new Articolo("giornale", 1);
		articolo1ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine3);
		if (articolo1ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine3 = new Articolo("giornale", 1);
		articolo2ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine3);
		if (articolo2ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo3ordine3 = new Articolo("giornale", 1);
		articolo3ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo3ordine3);
		if (articolo3ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// poi inserisco 4 categorie
		Categoria categoria1 = new Categoria("thriller", "00a1");
		categoriaServiceInstance.inserisciNuovo(categoria1);
		if (categoria1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria2 = new Categoria("rosso", "00f4");
		categoriaServiceInstance.inserisciNuovo(categoria2);
		if (categoria2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria3 = new Categoria("horror", "00j9");
		categoriaServiceInstance.inserisciNuovo(categoria3);
		if (categoria3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria4 = new Categoria("giallo", "05z1");
		categoriaServiceInstance.inserisciNuovo(categoria4);
		if (categoria4.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// associo le categorie agli articoli...
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine1, categoria1);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine1, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine2, categoria3);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine3, categoria4);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine3, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo3ordine3, categoria3);
		
		// associo gli articoli agli ordini
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo1ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo2ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine2, articolo1ordine2);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo1ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo2ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo3ordine3);
		
		// eseguo la query
		List<Ordine> listaDiOrdini = ordineServiceInstance.ordiniPerCategoria(categoria2);
		if (listaDiOrdini.size() < 2) 
			throw new RuntimeException("non è stato possibile eseguire la query");
		
		
		System.out.println(".......... FINE testTrovaOrdiniDataCategoria: successo ..........");
	}
	
	private static void testTrovaCategorieDatiOrdini(ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testTrovaCategorieDatiOrdini ..........");
		
		// inserisco 3 ordini
		Ordine ordine1 = new Ordine("mario verdi", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine1);
		if (ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Ordine ordine2 = new Ordine("matteo scarcella", "via walter tobagi");
		ordineServiceInstance.inserisciNuovo(ordine2);
		if (ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Ordine ordine3 = new Ordine("maria rossi", "via armando mecali");
		ordineServiceInstance.inserisciNuovo(ordine3);
		if (ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// inserisco articoli per ogni ordine
		Articolo articolo1ordine1 = new Articolo("giornale", 1);
		articolo1ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine1);
		if (articolo1ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine1 = new Articolo("giornale", 10);
		articolo2ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine1);
		if (articolo2ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine2 = new Articolo("giornale", 12);
		articolo1ordine2.setOrdine(ordine2); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine2);
		if (articolo1ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine3 = new Articolo("giornale", 13);
		articolo1ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine3);
		if (articolo1ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine3 = new Articolo("giornale", 15);
		articolo2ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine3);
		if (articolo2ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo3ordine3 = new Articolo("giornale", 7);
		articolo3ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo3ordine3);
		if (articolo3ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// poi inserisco 4 categorie
		Categoria categoria1 = new Categoria("thriller", "00a1");
		categoriaServiceInstance.inserisciNuovo(categoria1);
		if (categoria1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria2 = new Categoria("rosso", "00f4");
		categoriaServiceInstance.inserisciNuovo(categoria2);
		if (categoria2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria3 = new Categoria("horror", "00j9");
		categoriaServiceInstance.inserisciNuovo(categoria3);
		if (categoria3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria4 = new Categoria("giallo", "05z1");
		categoriaServiceInstance.inserisciNuovo(categoria4);
		if (categoria4.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// associo le categorie agli articoli...
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine1, categoria1);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine1, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine2, categoria3);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine3, categoria4);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine3, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo3ordine3, categoria3);
		
		// associo gli articoli agli ordini
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo1ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo2ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine2, articolo1ordine2);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo1ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo2ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo3ordine3);
		
		// eseguo la query
		List<Categoria> listaDiCategorie = categoriaServiceInstance.trovaTutteCategorieDistinteDatoOrdine(ordine3);
		if (listaDiCategorie.size() < 3) 
			throw new RuntimeException("non è stato possibile eseguire la query");
		
		
		System.out.println(".......... FINE testTrovaCategorieDatiOrdini: successo ..........");
	}
	
	private static void testSommaArticoliDatoOrdine(ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testSommaArticoliDatoOrdine ..........");
		
		// inserisco 3 ordini
		Ordine ordine1 = new Ordine("mario verdi", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine1);
		if (ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Ordine ordine2 = new Ordine("matteo scarcella", "via walter tobagi");
		ordineServiceInstance.inserisciNuovo(ordine2);
		if (ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Ordine ordine3 = new Ordine("maria rossi", "via armando mecali");
		ordineServiceInstance.inserisciNuovo(ordine3);
		if (ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// inserisco articoli per ogni ordine
		Articolo articolo1ordine1 = new Articolo("giornale", 1);
		articolo1ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine1);
		if (articolo1ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine1 = new Articolo("giornale", 10);
		articolo2ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine1);
		if (articolo2ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine2 = new Articolo("giornale", 12);
		articolo1ordine2.setOrdine(ordine2); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine2);
		if (articolo1ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine3 = new Articolo("giornale", 13);
		articolo1ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine3);
		if (articolo1ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine3 = new Articolo("giornale", 15);
		articolo2ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine3);
		if (articolo2ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo3ordine3 = new Articolo("giornale", 7);
		articolo3ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo3ordine3);
		if (articolo3ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// poi inserisco 4 categorie
		Categoria categoria1 = new Categoria("thriller", "00a1");
		categoriaServiceInstance.inserisciNuovo(categoria1);
		if (categoria1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria2 = new Categoria("rosso", "00f4");
		categoriaServiceInstance.inserisciNuovo(categoria2);
		if (categoria2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria3 = new Categoria("horror", "00j9");
		categoriaServiceInstance.inserisciNuovo(categoria3);
		if (categoria3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria4 = new Categoria("giallo", "05z1");
		categoriaServiceInstance.inserisciNuovo(categoria4);
		if (categoria4.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// associo le categorie agli articoli...
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine1, categoria1);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine1, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine2, categoria3);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine3, categoria4);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine3, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo3ordine3, categoria3);
		
		// associo gli articoli agli ordini
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo1ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo2ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine2, articolo1ordine2);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo1ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo2ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo3ordine3);
		
		// eseguo la query
		Long sommaArticoli = articoloServiceInstance.sommaPrezziDataCategoria(categoria3);
		if (sommaArticoli == null) {
			throw new RuntimeException("non è stato possibile eseguire la query");
		}
			
		System.out.println(".......... FINE testSommaArticoliDatoOrdine: successo ..........");
	}
	
	private static void testOrdinePiuRecente(ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testOrdinePiuRecente ..........");
		
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(2022, 1, 1);
		date = c.getTime();
		
		// inserisco 3 ordini
		Ordine ordine1 = new Ordine("mario verdi", "via pietro belon", date);
		ordineServiceInstance.inserisciNuovo(ordine1);
		if (ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		c.set(2021, 1, 1);
		date = c.getTime();
		
		Ordine ordine2 = new Ordine("matteo scarcella", "via walter tobagi", date);
		ordineServiceInstance.inserisciNuovo(ordine2);
		if (ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		c.set(2020, 1, 1);
		date = c.getTime();
		
		Ordine ordine3 = new Ordine("maria rossi", "via armando mecali", date);
		ordineServiceInstance.inserisciNuovo(ordine3);
		if (ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// inserisco articoli per ogni ordine
		Articolo articolo1ordine1 = new Articolo("giornale", 1);
		articolo1ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine1);
		if (articolo1ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine1 = new Articolo("giornale", 10);
		articolo2ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine1);
		if (articolo2ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine2 = new Articolo("giornale", 12);
		articolo1ordine2.setOrdine(ordine2); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine2);
		if (articolo1ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine3 = new Articolo("giornale", 13);
		articolo1ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine3);
		if (articolo1ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine3 = new Articolo("giornale", 15);
		articolo2ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine3);
		if (articolo2ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo3ordine3 = new Articolo("giornale", 7);
		articolo3ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo3ordine3);
		if (articolo3ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// poi inserisco 4 categorie
		Categoria categoria1 = new Categoria("thriller", "00a1");
		categoriaServiceInstance.inserisciNuovo(categoria1);
		if (categoria1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria2 = new Categoria("rosso", "00f4");
		categoriaServiceInstance.inserisciNuovo(categoria2);
		if (categoria2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria3 = new Categoria("horror", "00j9");
		categoriaServiceInstance.inserisciNuovo(categoria3);
		if (categoria3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria4 = new Categoria("giallo", "05z1");
		categoriaServiceInstance.inserisciNuovo(categoria4);
		if (categoria4.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// associo le categorie agli articoli...
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine1, categoria1);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine1, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine2, categoria3);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine3, categoria4);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine3, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo3ordine3, categoria3);
		
		// associo gli articoli agli ordini
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo1ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo2ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine2, articolo1ordine2);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo1ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo2ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo3ordine3);
		
		// eseguo la query
		c.set(2021, 1, 1);
		date = c.getTime();
		
		Ordine ordinePiuRecente = ordineServiceInstance.ordinePiuRecenteDataCategoria(categoria3);
		if (ordinePiuRecente.getDataSpedizione() != date) {
			throw new RuntimeException("non è stato possibile eseguire la query");
		}
			
		System.out.println(".......... FINE testOrdinePiuRecente: successo ..........");
	}
	
	private static void testCodiciCategoriaOrdiniFebbraio2022(ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testCodiciCategoriaOrdiniFebbraio2022 ..........");
		
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(2022, 0, 12);
		date = c.getTime();
		
		// inserisco 3 ordini
		Ordine ordine1 = new Ordine("mario verdi", "via pietro belon", date);
		ordineServiceInstance.inserisciNuovo(ordine1);
		if (ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		c.set(2021, 1, 7);
		date = c.getTime();
		
		Ordine ordine2 = new Ordine("matteo scarcella", "via walter tobagi", date);
		ordineServiceInstance.inserisciNuovo(ordine2);
		if (ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		c.set(2020, 1, 22);
		date = c.getTime();
		
		Ordine ordine3 = new Ordine("maria rossi", "via armando mecali", date);
		ordineServiceInstance.inserisciNuovo(ordine3);
		if (ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// inserisco articoli per ogni ordine
		Articolo articolo1ordine1 = new Articolo("giornale", 1);
		articolo1ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine1);
		if (articolo1ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine1 = new Articolo("giornale", 10);
		articolo2ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine1);
		if (articolo2ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine2 = new Articolo("giornale", 12);
		articolo1ordine2.setOrdine(ordine2); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine2);
		if (articolo1ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine3 = new Articolo("giornale", 13);
		articolo1ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine3);
		if (articolo1ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine3 = new Articolo("giornale", 15);
		articolo2ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine3);
		if (articolo2ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo3ordine3 = new Articolo("giornale", 7);
		articolo3ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo3ordine3);
		if (articolo3ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// poi inserisco 4 categorie
		Categoria categoria1 = new Categoria("thriller", "00a1");
		categoriaServiceInstance.inserisciNuovo(categoria1);
		if (categoria1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria2 = new Categoria("rosso", "00f4");
		categoriaServiceInstance.inserisciNuovo(categoria2);
		if (categoria2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria3 = new Categoria("horror", "00j9");
		categoriaServiceInstance.inserisciNuovo(categoria3);
		if (categoria3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria4 = new Categoria("giallo", "05z1");
		categoriaServiceInstance.inserisciNuovo(categoria4);
		if (categoria4.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// associo le categorie agli articoli...
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine1, categoria1);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine1, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine2, categoria3);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine3, categoria4);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine3, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo3ordine3, categoria3);
		
		// associo gli articoli agli ordini
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo1ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo2ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine2, articolo1ordine2);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo1ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo2ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo3ordine3);
		
		// eseguo la query
		List<String> listaCodici = categoriaServiceInstance.codiciCategorieOrdiniFebbraio2022();
		if (listaCodici.isEmpty()) {
			throw new RuntimeException("non è stato possibile estrarre i codicis");
		}
			
		System.out.println(".......... FINE testCodiciCategoriaOrdiniFebbraio2022: successo ..........");
	}
	
	private static void testSommaPrezziArticoliOrdineMarioRossi(ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testSommaPrezziArticoliOrdineMarioRossi ..........");
		
		// inserisco 3 ordini
		Ordine ordine1 = new Ordine("mario rossi", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine1);
		if (ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Ordine ordine2 = new Ordine("matteo scarcella", "via walter tobagi");
		ordineServiceInstance.inserisciNuovo(ordine2);
		if (ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Ordine ordine3 = new Ordine("maria rossi", "via armando mecali");
		ordineServiceInstance.inserisciNuovo(ordine3);
		if (ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// inserisco articoli per ogni ordine
		Articolo articolo1ordine1 = new Articolo("giornale", 1);
		articolo1ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine1);
		if (articolo1ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine1 = new Articolo("giornale", 10);
		articolo2ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine1);
		if (articolo2ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine2 = new Articolo("giornale", 12);
		articolo1ordine2.setOrdine(ordine2); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine2);
		if (articolo1ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine3 = new Articolo("giornale", 13);
		articolo1ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine3);
		if (articolo1ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine3 = new Articolo("giornale", 15);
		articolo2ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine3);
		if (articolo2ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo3ordine3 = new Articolo("giornale", 7);
		articolo3ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo3ordine3);
		if (articolo3ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// poi inserisco 4 categorie
		Categoria categoria1 = new Categoria("thriller", "00a1");
		categoriaServiceInstance.inserisciNuovo(categoria1);
		if (categoria1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria2 = new Categoria("rosso", "00f4");
		categoriaServiceInstance.inserisciNuovo(categoria2);
		if (categoria2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria3 = new Categoria("horror", "00j9");
		categoriaServiceInstance.inserisciNuovo(categoria3);
		if (categoria3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria4 = new Categoria("giallo", "05z1");
		categoriaServiceInstance.inserisciNuovo(categoria4);
		if (categoria4.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// associo le categorie agli articoli...
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine1, categoria1);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine1, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine2, categoria3);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine3, categoria4);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine3, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo3ordine3, categoria3);
		
		// associo gli articoli agli ordini
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo1ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo2ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine2, articolo1ordine2);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo1ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo2ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo3ordine3);
		
		// eseguo la query
		Long sommaPrezzi = articoloServiceInstance.sommaPrezziMarioRossi();
		if (sommaPrezzi == null) {
			throw new RuntimeException("non è stato possibile calcolare la somma dei prezzi di Mario Rossi");
		}
			
		System.out.println(".......... FINE testSommaPrezziArticoliOrdineMarioRossi: successo ..........");
	}
	
	private static void testTrovaIndirizziOrdiniConNumeroSeriale(ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......... INIZIO testTrovaIndirizziOrdiniConNumeroSeriale ..........");
		
		// inserisco 3 ordini
		Ordine ordine1 = new Ordine("mario rossi", "via pietro belon");
		ordineServiceInstance.inserisciNuovo(ordine1);
		if (ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Ordine ordine2 = new Ordine("matteo scarcella", "via walter tobagi");
		ordineServiceInstance.inserisciNuovo(ordine2);
		if (ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Ordine ordine3 = new Ordine("maria rossi", "via armando mecali");
		ordineServiceInstance.inserisciNuovo(ordine3);
		if (ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// inserisco articoli per ogni ordine
		Articolo articolo1ordine1 = new Articolo("giornale", "CISB", 1);
		articolo1ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine1);
		if (articolo1ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine1 = new Articolo("giornale", "CEJNA", 10);
		articolo2ordine1.setOrdine(ordine1); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine1);
		if (articolo2ordine1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine2 = new Articolo("giornale", "RIBXS", 12);
		articolo1ordine2.setOrdine(ordine2); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine2);
		if (articolo1ordine2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo1ordine3 = new Articolo("giornale", "PRIJC", 13);
		articolo1ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo1ordine3);
		if (articolo1ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo2ordine3 = new Articolo("giornale", "ECJIN", 15);
		articolo2ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo2ordine3);
		if (articolo2ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Articolo articolo3ordine3 = new Articolo("giornale", "RVJCN", 7);
		articolo3ordine3.setOrdine(ordine3); 		// perche articolo è nullable=false
		articoloServiceInstance.inserisciNuovo(articolo3ordine3);
		if (articolo3ordine3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// poi inserisco 4 categorie
		Categoria categoria1 = new Categoria("thriller", "00a1");
		categoriaServiceInstance.inserisciNuovo(categoria1);
		if (categoria1.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria2 = new Categoria("rosso", "00f4");
		categoriaServiceInstance.inserisciNuovo(categoria2);
		if (categoria2.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria3 = new Categoria("horror", "00j9");
		categoriaServiceInstance.inserisciNuovo(categoria3);
		if (categoria3.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		Categoria categoria4 = new Categoria("giallo", "05z1");
		categoriaServiceInstance.inserisciNuovo(categoria4);
		if (categoria4.getId() == null)
			throw new RuntimeException("non è stato possibile inserire il record");
		
		// associo le categorie agli articoli...
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine1, categoria1);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine1, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine2, categoria3);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo1ordine3, categoria4);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo2ordine3, categoria2);
		articoloServiceInstance.aggiungiCategoriaAdArticolo(articolo3ordine3, categoria3);
		
		// associo gli articoli agli ordini
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo1ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine1, articolo2ordine1);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine2, articolo1ordine2);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo1ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo2ordine3);
		ordineServiceInstance.aggiungiArticoloAdOrdine(ordine3, articolo3ordine3);
		
		// eseguo la query
		List<String> listaDiIndirizzi = ordineServiceInstance.indirizziDiOrdiniConSerialeInArticolo("J");
		if (listaDiIndirizzi.size() != 2) {
			throw new RuntimeException("non è stato possibile trovare correttamente gli indirizzi");
		}
			
		System.out.println(".......... FINE testTrovaIndirizziOrdiniConNumeroSeriale: successo ..........");
	}
}
