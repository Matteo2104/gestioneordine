package it.prova.gestioneordine.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "articolo")
public class Articolo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "numeroseriale")
	private String numeroSeriale;
	@Column(name = "prezzosingolo")
	private int prezzoSingolo;
	@Column(name = "datainserimento")
	private Date dataInserimento;
	
	// campi per le time info del record
	@CreationTimestamp
	private LocalDateTime createDateTime;
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordine_id", nullable=false)
	private Ordine ordine;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "articolo_categoria", joinColumns = @JoinColumn(name = "articolo_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "ID"))
	private Set<Categoria> categorie = new HashSet<Categoria>();
	
	public Articolo() {}
	public Articolo(String descrizione, int prezzoSingolo) {
		this.descrizione = descrizione;
		this.prezzoSingolo = prezzoSingolo;
	}

	public Long getId() {
		return id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getNumeroSeriale() {
		return numeroSeriale;
	}

	public int getPrezzoSingolo() {
		return prezzoSingolo;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public Set<Categoria> getCategorie() {
		return categorie;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setNumeroSeriale(String numeroSeriale) {
		this.numeroSeriale = numeroSeriale;
	}

	public void setPrezzoSingolo(int prezzoSingolo) {
		this.prezzoSingolo = prezzoSingolo;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public void setCategorie(Set<Categoria> categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Articolo [id=" + id + ", descrizione=" + descrizione + ", numeroSeriale=" + numeroSeriale
				+ ", prezzoSingolo=" + prezzoSingolo + ", dataInserimento=" + dataInserimento + ", createDateTime="
				+ createDateTime + ", updateDateTime=" + updateDateTime + "]";
	}
	
	public void removeFromCategorie(Categoria categoriaToRemove) {
		this.categorie.remove(categoriaToRemove);
		categoriaToRemove.getArticoli().remove(this);
	}

}
