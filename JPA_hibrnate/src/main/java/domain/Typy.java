package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Typy {
	@Id
 private long id;
 private int[] intArray;
 private double[] doubleArray;
 private MyEnum myEnum;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public int[] getIntArray() {
	return intArray;
}
public void setIntArray(int[] intArray) {
	this.intArray = intArray;
}
public double[] getDoubleArray() {
	return doubleArray;
}
public void setDoubleArray(double[] doubleArray) {
	this.doubleArray = doubleArray;
}
public MyEnum getMyEnum() {
	return myEnum;
}
public void setMyEnum(MyEnum myEnum) {
	this.myEnum = myEnum;
}
	
}
