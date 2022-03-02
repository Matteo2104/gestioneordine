package it.prova.gestioneordine.service.ordine;

import java.util.List;

import it.prova.gestioneordine.dao.ordine.OrdineDAO;
import it.prova.gestioneordine.model.Articolo;
import it.prova.gestioneordine.model.Categoria;
import it.prova.gestioneordine.model.Ordine;


public interface OrdineService {
	public List<Ordine> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Ordine ordineInstance) throws Exception;

	// per injection
	public void setOrdineDAO(OrdineDAO ordineDAO);

	public void aggiungiArticoloAdOrdine(Ordine ordineInstance, Articolo articoloInstance) throws Exception;
	
	public Ordine caricaSingoloElementoEager(Long id) throws Exception;

	// esercizi per casa
	public List<Ordine> ordiniPerCategoria(Categoria categoria) throws Exception;

	public Ordine ordinePiuRecenteDataCategoria(Categoria categoria) throws Exception;

	List<String> indirizziDiOrdiniConSerialeInArticolo(String pezzoDiSeriale) throws Exception;
}
