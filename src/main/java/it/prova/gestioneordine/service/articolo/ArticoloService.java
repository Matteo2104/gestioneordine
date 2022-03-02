package it.prova.gestioneordine.service.articolo;

import java.util.List;

import it.prova.gestioneordine.dao.articolo.ArticoloDAO;
import it.prova.gestioneordine.dao.categoria.CategoriaDAO;
import it.prova.gestioneordine.model.Articolo;
import it.prova.gestioneordine.model.Categoria;

public interface ArticoloService {
	// crud classico
	public List<Articolo> listAll() throws Exception;

	public Articolo caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Articolo articoloInstance) throws Exception;

	public void inserisciNuovo(Articolo articoloInstance) throws Exception;

	public void rimuovi(Articolo articoloInstance) throws Exception;

	// per injection
	public void setArticoloDAO(ArticoloDAO articoloDAO);
	public void setCategoriaDAO(CategoriaDAO categoriaDAO);

	
	// utilities
	public void aggiungiCategoriaAdArticolo(Articolo articoloInstance, Categoria categoriaInstance) throws Exception;

	public Articolo caricaSingoloElementoEager(Long id) throws Exception;

	void rimuoviCategoria(Articolo articolo, Categoria categoria) throws Exception;

	Long sommaPrezziDataCategoria(Categoria categoria) throws Exception;

	Long sommaPrezziMarioRossi() throws Exception;

	
}
