package de.comcave.maxron;


import java.util.ArrayList;

public class Wartezimmer {
	
	private ArrayList<Patient> patientenliste;

	public Wartezimmer(ArrayList<Patient> eingabeListe) {
		this.patientenliste = eingabeListe;
	};
	
	public void aufrufenPatient() {
		if (this.patientenliste.size() > 0) {
			System.out.println("Der nächste Patient: ");
			System.out.println(this.patientenliste.get(0) + "\n");
			this.patientenliste.remove(0);
		} else {
			System.out.println("\nKeine Patienten mehr im Wartezimmer!\n");
		}
	};
	
	public void verlaesstPraxisPatient(Patient pat) {
		this.patientenliste.remove(pat);
		System.out.println("Der Patient " + pat.getVorname() + " "
						 + pat.getNachname() + " verlässt die Praxis.\n");
	};	
}
