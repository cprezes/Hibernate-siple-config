package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DataTime {
@Id
private long id;

private java.sql.Date data;
private java.sql.Time czas;
@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")  //definicja typu z apisana SQL
private java.sql.Timestamp timestamp;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public java.sql.Date getData() {
	return data;
}
public void setData(java.sql.Date data) {
	this.data = data;
}
public java.sql.Time getCzas() {
	return czas;
}
public void setCzas(java.sql.Time czas) {
	this.czas = czas;
}
public java.sql.Timestamp getTimestamp() {
	return timestamp;
}
public void setTimestamp(java.sql.Timestamp timestamp) {
	this.timestamp = timestamp;
}

	
}
