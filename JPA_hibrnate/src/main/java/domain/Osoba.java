package domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity   //zmienia typ obiektu jako zapisywalny
@Table(name="Osoba") //nazwa tabeli
public class Osoba {
	@Id
	@GeneratedValue //pozwala na dodawanie  id automatycznie 
	private long id;
	@Column(name="Imie") // pozwala wyswietlić lub zapisać dane w kolumnie o nastepucej nazwie
	private String imie;
	@Column(name="nazwisko",nullable=false ,length=30) //można ustwaić że kolumn anie może być null  i jej dlugość 
	private String nazwisko;
	@Column(name="kwota")
	private double wynagrodzenie;
	@Column(precision=10 ,scale=2) // liczba 10 zanków z 2 mijecami po przecinku
	private BigDecimal salary;
	@Transient    //tego pola nie chce dopisywac do bazy
	private int ingnored;
	

	public int getIngnored() {
		return ingnored;
	}

	public void setIngnored(int ingnored) {
		this.ingnored = ingnored;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public double getWynagrodzenie() {
		return wynagrodzenie;
	}

	public void setWynagrodzenie(double wynagrodzenie) {
		this.wynagrodzenie = wynagrodzenie;
	}
}
