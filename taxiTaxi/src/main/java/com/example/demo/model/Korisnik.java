package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_korisnik")
	private int idKorisnik;

	private String email;

	private String ime;

	private int obrisan;

	private String pass;

	private String prezime;

	private String telefon;

	private String username;

	//bi-directional many-to-one association to Uloga
	@ManyToOne
	private Uloga uloga;

	//bi-directional many-to-one association to Voznja
	@OneToMany(mappedBy="korisnik")
	private List<Voznja> poruceno;

	//bi-directional many-to-one association to Voznja
	@OneToMany(mappedBy="vozac")
	private List<Voznja> vozeno;

	public Korisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public int getObrisan() {
		return this.obrisan;
	}

	public void setObrisan(int obrisan) {
		this.obrisan = obrisan;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Uloga getUloga() {
		return this.uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public List<Voznja> getPoruceno() {
		return this.poruceno;
	}

	public void setPoruceno(List<Voznja> poruceno) {
		this.poruceno = poruceno;
	}

	public Voznja addPoruceno(Voznja poruceno) {
		getPoruceno().add(poruceno);
		poruceno.setKorisnik(this);

		return poruceno;
	}

	public Voznja removePoruceno(Voznja poruceno) {
		getPoruceno().remove(poruceno);
		poruceno.setKorisnik(null);

		return poruceno;
	}

	public List<Voznja> getVozeno() {
		return this.vozeno;
	}

	public void setVozeno(List<Voznja> vozeno) {
		this.vozeno = vozeno;
	}

	public Voznja addVozeno(Voznja vozeno) {
		getVozeno().add(vozeno);
		vozeno.setVozac(this);

		return vozeno;
	}

	public Voznja removeVozeno(Voznja vozeno) {
		getVozeno().remove(vozeno);
		vozeno.setVozac(null);

		return vozeno;
	}

}