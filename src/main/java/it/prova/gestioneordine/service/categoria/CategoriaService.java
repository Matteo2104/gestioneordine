package it.prova.gestioneordine.service.categoria;

import java.util.List;

import it.prova.gestioneordine.dao.categoria.CategoriaDAO;
import it.prova.gestioneordine.model.Articolo;
import it.prova.gestioneordine.model.Categoria;
import it.prova.gestioneordine.model.Ordine;

public interface CategoriaService {
	public List<Categoria> listAll() throws Exception;

	public Categoria caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Categoria categoriaInstance) throws Exception;

	public void inserisciNuovo(Categoria categoriaInstance) throws Exception;

	public void rimuovi(Categoria categoriaInstance) throws Exception;

	// per injection
	public void setCategoriaDAO(CategoriaDAO categoriaDAO);

	public void aggiungiArticoloACategoria(Categoria categoriaInstance, Articolo articoloInstance) throws Exception;

	public Categoria caricaSingoloElementoEager(Long id) throws Exception;

	void rimuoviArticolo(Categoria categoriaInstance, Articolo articoloInstance) throws Exception;

	List<Categoria> trovaTutteCategorieDistinteDatoOrdine(Ordine ordine) throws Exception;

}
