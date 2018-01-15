package domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity // kalasa ralcyjna musi miec entity
public class Samochod {
@Id // jesli ma entity to musi miec id 
@GeneratedValue  //autmatyczna genracja id
private long id;
private String marka;
private String model;

@OneToMany(mappedBy="samochod") //przy mapowaniu do wielu pramter musli byc lista 
private List< Pracownik> pracownik;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id; 
}
public String getMarka() {
	return marka;
}
public void setMarka(String marka) {
	this.marka = marka;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}


}
