package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the voznja database table.
 * 
 */
@Entity
@NamedQuery(name="Voznja.findAll", query="SELECT v FROM Voznja v")
public class Voznja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_voznja")
	private int idVoznja;

	@Column(name="adresa_cilj")
	private String adresaCilj;

	@Column(name="adresa_polazak")
	private String adresaPolazak;

	@Column(name="status_voznje")
	private String statusVoznje;

	@Column(name="telefon_mob")
	private String telefonMob;

	@Column(name="ukupno_km")
	private int ukupnoKm;

	@Column(name="vreme_polazak")
	private Timestamp vremePolazak;

	//bi-directional many-to-one association to Racun
	@OneToMany(mappedBy="voznja")
	private List<Racun> racuns;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik vozac;

	public Voznja() {
	}

	public int getIdVoznja() {
		return this.idVoznja;
	}

	public void setIdVoznja(int idVoznja) {
		this.idVoznja = idVoznja;
	}

	public String getAdresaCilj() {
		return this.adresaCilj;
	}

	public void setAdresaCilj(String adresaCilj) {
		this.adresaCilj = adresaCilj;
	}

	public String getAdresaPolazak() {
		return this.adresaPolazak;
	}

	public void setAdresaPolazak(String adresaPolazak) {
		this.adresaPolazak = adresaPolazak;
	}

	public String getStatusVoznje() {
		return this.statusVoznje;
	}

	public void setStatusVoznje(String statusVoznje) {
		this.statusVoznje = statusVoznje;
	}

	public String getTelefonMob() {
		return this.telefonMob;
	}

	public void setTelefonMob(String telefonMob) {
		this.telefonMob = telefonMob;
	}

	public int getUkupnoKm() {
		return this.ukupnoKm;
	}

	public void setUkupnoKm(int ukupnoKm) {
		this.ukupnoKm = ukupnoKm;
	}

	public Timestamp getVremePolazak() {
		return this.vremePolazak;
	}

	public void setVremePolazak(Timestamp vremePolazak) {
		this.vremePolazak = vremePolazak;
	}

	public List<Racun> getRacuns() {
		return this.racuns;
	}

	public void setRacuns(List<Racun> racuns) {
		this.racuns = racuns;
	}

	public Racun addRacun(Racun racun) {
		getRacuns().add(racun);
		racun.setVoznja(this);

		return racun;
	}

	public Racun removeRacun(Racun racun) {
		getRacuns().remove(racun);
		racun.setVoznja(null);

		return racun;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Korisnik getVozac() {
		return this.vozac;
	}

	public void setVozac(Korisnik vozac) {
		this.vozac = vozac;
	}

	@Override
	public String toString() {
		return "Voznja{" +
				"idVoznja=" + idVoznja +
				", adresaCilj='" + adresaCilj + '\'' +
				", adresaPolazak='" + adresaPolazak + '\'' +
				", statusVoznje='" + statusVoznje + '\'' +
				", telefonMob='" + telefonMob + '\'' +
				", ukupnoKm=" + ukupnoKm +
				", vremePolazak=" + vremePolazak +
				", racuns=" + racuns +
				", korisnik=" + korisnik +
				", vozac=" + vozac +
				'}';
	}
}