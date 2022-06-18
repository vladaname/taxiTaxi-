package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the racun database table.
 * 
 */
@Entity
@NamedQuery(name="Racun.findAll", query="SELECT r FROM Racun r")
public class Racun implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_racun")
	private int idRacun;

	@Column(name="ukupan_racun")
	private double ukupanRacun;

	//bi-directional many-to-one association to Cenovnik
	@ManyToOne
	private Cenovnik cenovnik;

	//bi-directional many-to-one association to Voznja
	@ManyToOne
	private Voznja voznja;

	public Racun() {
	}

	public int getIdRacun() {
		return this.idRacun;
	}

	public void setIdRacun(int idRacun) {
		this.idRacun = idRacun;
	}

	public double getUkupanRacun() {
		return this.ukupanRacun;
	}

	public void setUkupanRacun(double ukupanRacun) {
		this.ukupanRacun = ukupanRacun;
	}

	public Cenovnik getCenovnik() {
		return this.cenovnik;
	}

	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
	}

	public Voznja getVoznja() {
		return this.voznja;
	}

	public void setVoznja(Voznja voznja) {
		this.voznja = voznja;
	}

}