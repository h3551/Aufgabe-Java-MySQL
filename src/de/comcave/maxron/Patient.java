package de.comcave.maxron;


public class Patient {
	
	private String vorname;
	private String nachname;
	private int alter;
	private String krankheit;
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public int getAlter() {
		return alter;
	}
	public void setAlter(int alter) {
		this.alter = alter;
	}
	public String getKrankheit() {
		return krankheit;
	}
	public void setKrankheit(String krankheit) {
		this.krankheit = krankheit;
	}

	public Patient() {
		this.setNachname("Standard Nachname");
		this.setVorname("Standard Vorname");
		this.setAlter(0);
		this.setKrankheit("Generisch");
	};
	
	public Patient(String vorname, String nachname, int alter, String krankheit) {
		this.setVorname(vorname);
		this.setNachname(nachname);
		this.setAlter(alter);
		this.setKrankheit(krankheit);
	};
	
	@Override
	public String toString() {
		return this.getVorname() + " "
			 + this.getNachname() + ", "
			 + this.getAlter() + ", "
			 + this.getKrankheit();
	};	
}
