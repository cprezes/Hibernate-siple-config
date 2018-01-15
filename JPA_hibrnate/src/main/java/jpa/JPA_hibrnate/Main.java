package jpa.JPA_hibrnate;

import static org.hamcrest.CoreMatchers.containsString;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.objenesis.ObjenesisException;

import domain.CoLubi;
import domain.DataTime;
import domain.Osoba;
import domain.Pracownik;
import domain.Samochod;

public class Main {
	private static EntityManager entityManager;

	public static  void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mojaBaza"); // <persistence-unit
																										// name="mojaBaza"
	 entityManager = entityManagerFactory.createEntityManager();
		
		Osoba osoba = new Osoba();
		Osoba osoba1 = new Osoba();
		
	//	osoba.setId(1); // można dodać id pod jakim chcemy zapisać 
		osoba.setImie("Buda");
		osoba.setNazwisko("darek");
		osoba.setWynagrodzenie(22321);
		osoba.setSalary ( new  BigDecimal(123.31));
		osoba1.setImie("kuba");
		osoba1.setNazwisko("kaminski");
		osoba1.setWynagrodzenie(22321);
		osoba1.setSalary ( new  BigDecimal(234.21));
	 	DataTime data=new DataTime();
		
	 	data.setCzas(new Time(2331));
	 	
	 	Pracownik pracownik = new Pracownik();
	 	
	 	pracownik.setImie("Jan");
	 	pracownik.setNazwisko("Kowal");
	 	pracownik.setWyplata(2131);
	 	pracownik.setNumerDomu(12);
	 	pracownik.setUlica("Ulicowa");
	 	pracownik.setKodPocztowy("231231");
	 	pracownik.setMmiejscowosc("Miejscowy");	  	
	 	CoLubi coLubi = new CoLubi();
	 	pracownik.setCoLubi(coLubi);
	 	coLubi.aktwynosc="bieganie";
	 	coLubi.liczba=7;
	 	coLubi.przedmiot="zabwawki";
		
		Samochod samochod = new Samochod();
		samochod.setMarka("Opel");
		samochod.setModel("duda");
		pracownik.setSamochod(samochod);

		listaDoDodania();
	 	//przed zapisaniem do bazy nalerzy pobrac transakcje 
		entityManager.getTransaction().begin();
		entityManager.persist(osoba); //zapisujemy do bazy danych
		entityManager.persist(osoba1);
		entityManager.persist(data);
		entityManager.persist(pracownik);
		entityManager.persist(samochod); // Uwaga jesli mamy relacje to musimy zapisac tez tabelea w któeja mamy relacje 
																
		Osoba pobarana = entityManager.find(Osoba.class,1L);   //pobierz osobę której id jest 1 musimy dodac L jako typ LONG
		wypisz( pobarana.getImie());
		pobarana.setWynagrodzenie(11111);  //Zmien tej osobie wynagrodzenie
		

		// Bdowanie zapytań 
		
		TypedQuery<Osoba> createQuery = entityManager.createQuery("FROM Osoba WHERE nazwisko='Jnaek'", Osoba.class);
		Osoba pobranaOsoba = createQuery.getSingleResult();
		wypisz(pobranaOsoba.getNazwisko()+" "+pobarana.getImie()+ "-----------------------------------");
		
		//Lista obiektów 
		
		  createQuery = entityManager.createQuery("FROM Osoba WHERE wynagrodzenie>300 ORDER BY wynagrodzenie ", Osoba.class);
		  List<Osoba> listaOsoba = createQuery.getResultList();
		  for (Osoba osoba2 : listaOsoba) {
			wypisz(osoba2.getNazwisko()+" "+osoba2.getImie()+ "-----------------------------------");
		}
		  
		  
		  //Dość niebezpieczne ale jak sie inaczej nieda to mżna i tak 
		  //Budujemy zapytanie nie jako OSOBA, w którym nie wiadomo co bedzie na wyjsciu
		  
		  Query zapytanie2 = entityManager.createQuery("SELECT CONCAT(imie, ' ',nazwisko ) ,wynagrodzenie*0.2  FROM Osoba" ); //bez mapownia na obiektu
		  Iterator iterator = zapytanie2.getResultList().iterator(); //musimy dlatego zbudować iterator
		  while (iterator.hasNext()) {                                // iterowanie po obiekcie 
			Object[] object = (Object[]) iterator.next();
			wypisz(object[0].toString() + " kwota do zapłaty  " + object[1].toString());	
		}
		  
		//bezpieczniejsza metoda   czyli dodanie parametów  nazwana czyli  :lczba
		  createQuery = entityManager.createQuery("FROM Osoba WHERE wynagrodzenie > :liczba ", Osoba.class);
		  createQuery.setParameter("liczba", 300.0);
		   listaOsoba = createQuery.getResultList();
		  
			  for (Osoba osoba2 : listaOsoba) {
					wypisz(osoba2.getNazwisko()+" "+osoba2.getImie()+ "-------------- 1 parmaetr nazwany  ------------");
				}
		   //dodawnaie parmatrów jako liczba (argumenty )
			  createQuery = entityManager.createQuery("FROM Osoba WHERE wynagrodzenie > ?1 AND  wynagrodzenie < ?2 ", Osoba.class);
			  createQuery.setParameter(1, 300.0);
			  createQuery.setParameter(2, 3000.0);
			  listaOsoba = createQuery.getResultList();
			  
				  for (Osoba osoba2 : listaOsoba) {
						wypisz(osoba2.getNazwisko()+" "+osoba2.getImie()+ "---------  2 Prametry --------------------------");
					}
			   
		   
				   //dodawnaie parmatrów jako LISTA [Tablica] Parmetrów 
				  createQuery = entityManager.createQuery("FROM Osoba WHERE nazwisko IN :nazwy ", Osoba.class);
				  List<String> nazwy = new ArrayList<String>();

				  nazwy.add("Bore");
				  nazwy.add("Loko");
				  createQuery.setParameter("nazwy", nazwy);
				  listaOsoba = createQuery.getResultList();
				  
					  for (Osoba osoba2 : listaOsoba) {
							wypisz(osoba2.getNazwisko()+" "+osoba2.getImie()+ "---------  przez array liste --------------------------");
						}
				  
				  
			  
			  
			  
		
		entityManager.getTransaction().commit(); //konczymy transakje 
		entityManager.close();
		entityManagerFactory.close();
		
	
	}
	
	
	private static void listaDoDodania() {
		dodawanie("Darek","Dada", 53445);
		dodawanie("Dona","Fera", 534245);
		dodawanie("Byk","Jnaek", 544235);
		dodawanie("Rela","Den", 23545);
		dodawanie("Wacla","Wek", 51245);
		dodawanie("Wsok","Ssaa", 521345);
		dodawanie("Ewek","Maria", 512345);
		dodawanie("Serl","Loko", 123545);
		dodawanie("Poro","Lolo", 34545);
		dodawanie("Yujns","Bore", 34545);
		dodawanie("Cbcv","Dgdhg", 21545);
		dodawanie("Zeeed","Dfee", 345);
				
	}
	
	private static void dodawanie(String imie, String nazwisko,double wyplata){
		Osoba osoba = new Osoba();
		osoba.setImie(imie);
		osoba.setNazwisko(nazwisko);
		osoba.setWynagrodzenie(wyplata);
		entityManager.getTransaction().begin();
		entityManager.persist(osoba); //zapisujemy do bazy danych
		entityManager.getTransaction().commit(); //konczymy transakje 
	}
	
	
	private static void wypisz(String string) {
		System.out.println(string);
		
	}

	
	
}
