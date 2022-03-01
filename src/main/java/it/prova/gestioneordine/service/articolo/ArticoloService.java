package it.prova.gestioneordine.service.articolo;

import java.util.List;

import it.prova.gestioneordine.dao.articolo.ArticoloDAO;
import it.prova.gestioneordine.model.Articolo;


public interface ArticoloService {
	public List<Articolo> listAll() throws Exception;

	public Articolo caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Articolo articoloInstance) throws Exception;

	public void inserisciNuovo(Articolo articoloInstance) throws Exception;

	public void rimuovi(Articolo articoloInstance) throws Exception;

	// per injection
	public void setArticoloDAO(ArticoloDAO articoloDAO);
}
