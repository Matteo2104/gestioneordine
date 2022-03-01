package it.prova.gestioneordine.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "ordine")
public class Ordine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nomedestinatario")
	private String nomeDestinatario;
	@Column(name = "indirizzospedizione")
	private String indirizzoSpedizione;
	@Column(name = "dataspedizione")
	private Date dataSpedizione;

	// campi per le time info del record
	@CreationTimestamp
	private LocalDateTime createDateTime;
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ordine")
	private Set<Articolo> articoli = new HashSet<>();

	public Long getId() {
		return id;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}

	public Date getDataSpedizione() {
		return dataSpedizione;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public Set<Articolo> getArticoli() {
		return articoli;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public void setIndirizzoSpedizione(String indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}

	public void setDataSpedizione(Date dataSpedizione) {
		this.dataSpedizione = dataSpedizione;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public void setArticoli(Set<Articolo> articoli) {
		this.articoli = articoli;
	}

	@Override
	public String toString() {
		return "Ordine [id=" + id + ", nomeDestinatario=" + nomeDestinatario + ", indirizzoSpedizione="
				+ indirizzoSpedizione + ", dataSpedizione=" + dataSpedizione + ", createDateTime=" + createDateTime
				+ ", updateDateTime=" + updateDateTime + ", articoli=" + articoli + "]";
	}
	
	
}
