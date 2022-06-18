package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cenovnik database table.
 * 
 */
@Entity
@NamedQuery(name="Cenovnik.findAll", query="SELECT c FROM Cenovnik c")
public class Cenovnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cenovnik")
	private int idCenovnik;

	@Column(name="cena_dan")
	private double cenaDan;

	@Column(name="cena_noc")
	private double cenaNoc;

	@Column(name="cena_start")
	private double cenaStart;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datum_promene")
	private Date datumPromene;

	//bi-directional many-to-one association to Racun
	@OneToMany(mappedBy="cenovnik")
	private List<Racun> racuns;

	public Cenovnik() {
	}

	public int getIdCenovnik() {
		return this.idCenovnik;
	}

	public void setIdCenovnik(int idCenovnik) {
		this.idCenovnik = idCenovnik;
	}

	public double getCenaDan() {
		return this.cenaDan;
	}

	public void setCenaDan(double cenaDan) {
		this.cenaDan = cenaDan;
	}

	public double getCenaNoc() {
		return this.cenaNoc;
	}

	public void setCenaNoc(double cenaNoc) {
		this.cenaNoc = cenaNoc;
	}

	public double getCenaStart() {
		return this.cenaStart;
	}

	public void setCenaStart(double cenaStart) {
		this.cenaStart = cenaStart;
	}

	public Date getDatumPromene() {
		return this.datumPromene;
	}

	public void setDatumPromene(Date datumPromene) {
		this.datumPromene = datumPromene;
	}

	public List<Racun> getRacuns() {
		return this.racuns;
	}

	public void setRacuns(List<Racun> racuns) {
		this.racuns = racuns;
	}

	public Racun addRacun(Racun racun) {
		getRacuns().add(racun);
		racun.setCenovnik(this);

		return racun;
	}

	public Racun removeRacun(Racun racun) {
		getRacuns().remove(racun);
		racun.setCenovnik(null);

		return racun;
	}

}