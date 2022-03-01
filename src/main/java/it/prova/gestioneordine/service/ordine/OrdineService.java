package it.prova.gestioneordine.service.ordine;

import java.util.List;

import it.prova.gestioneordine.dao.ordine.OrdineDAO;
import it.prova.gestioneordine.model.Ordine;


public interface OrdineService {
	public List<Ordine> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Ordine ordineInstance) throws Exception;

	// per injection
	public void setOrdineDAO(OrdineDAO ordineDAO);
}