package domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity   //można dodać jaki parametr (name = "pracownik") ay zmienić nazwę encji aby użyc ją w zapytanich wielkość liter jest ważna 
@Table(name="Pracownik") // zminia tylko nazwę tabeli nie encji 
@SecondaryTable(name="Adresy" , pkJoinColumns=@PrimaryKeyJoinColumn(name= "id_pracownika"))   // jeśli nie podoba sic sie się id w kolumni adresy  to mojz dodanc kolumne wiazania za pmoca   pkJoinColumns
public class Pracownik {
@Id
@GeneratedValue
private int id;
private String imie;
private String nazwisko;
private double wyplata;

@Column(table="Adresy")
private String mmiejscowosc;
@Column(table="Adresy")
private String kodPocztowy;
@Column(table="Adresy")
private String ulica;
@Column(table="Adresy")
private int numerDomu;

@Embedded   //zagnierzdża klese w obiekcie aby zapisać ją do bazy
private CoLubi coLubi;

@ManyToOne       //tutaj dodajemy relację, odpowiednią relację 
@JoinColumn(name="samochodId") // nazwa kolumny gkóra chcemy porownywać 
private Samochod samochod; 

public Samochod getSamochod() {
	return samochod;
}
public void setSamochod(Samochod samochod) {
	this.samochod = samochod;
}
public CoLubi getCoLubi() {
	return coLubi;
}
public void setCoLubi(CoLubi coLubi) {
	this.coLubi = coLubi;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getImie() {
	return imie;
}
public void setImie(String imie) {
	this.imie = imie;
}
public String getNazwisko() {
	return nazwisko;
}
public void setNazwisko(String nazwisko) {
	this.nazwisko = nazwisko;
}
public double getWyplata() {
	return wyplata;
}
public void setWyplata(double wyplata) {
	this.wyplata = wyplata;
}
public String getMmiejscowosc() {
	return mmiejscowosc;
}
public void setMmiejscowosc(String mmiejscowosc) {
	this.mmiejscowosc = mmiejscowosc;
}
public String getKodPocztowy() {
	return kodPocztowy;
}
public void setKodPocztowy(String kodPocztowy) {
	this.kodPocztowy = kodPocztowy;
}
public String getUlica() {
	return ulica;
}
public void setUlica(String ulica) {
	this.ulica = ulica;
}
public int getNumerDomu() {
	return numerDomu;
}
public void setNumerDomu(int numerDomu) {
	this.numerDomu = numerDomu;
}

}
