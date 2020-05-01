package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class Blackout {

	Nerc nerc;
	int id;
	int customers;
	

	LocalDateTime dataInizio;
	LocalDateTime dataFine;
	
	int anno;
    long durata; 
	Duration d;
	
	
	
	
	public Blackout() {
		super();
	}
	public Blackout(Nerc nerc, int id, int customers, LocalDateTime dataInizio, LocalDateTime dataFine) {
		super();
		this.nerc = nerc;
		this.id = id;
		this.customers = customers;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		anno  = dataInizio.getYear();
		d = Duration.between(dataFine, dataInizio);
		durata = Math.abs(d.toHours());
		
		
		
	}
	@Override
	public String toString() {
		return anno+" "+dataInizio+" "+dataFine+" "+durata+" "+customers +"\n";
				
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blackout other = (Blackout) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public Nerc getNerc() {
		return nerc;
	}
	
	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomers() {
		return customers;
	}
	public void setCustomers(int customers) {
		this.customers = customers;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public long getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public LocalDateTime getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDateTime getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}

}
