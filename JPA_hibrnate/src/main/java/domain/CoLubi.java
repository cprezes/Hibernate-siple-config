package domain;

import javax.persistence.Embeddable;


@Embeddable
public class CoLubi {
	public String przedmiot;
	public int liczba;
	public String aktwynosc;
	
	public String getPrzedmiot() {
		return przedmiot;
	}
	public void setPrzedmiot(String przedmiot) {
		this.przedmiot = przedmiot;
	}
	public int getLiczba() {
		return liczba;
	}
	public void setLiczba(int liczba) {
		this.liczba = liczba;
	}
	public String getAktwynosc() {
		return aktwynosc;
	}
	public void setAktwynosc(String aktwynosc) {
		this.aktwynosc = aktwynosc;
	}

}
