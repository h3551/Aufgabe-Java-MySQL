package de.comcave.maxron;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Max Ronzani
 * @aufgabe MySQL Datenbank und Java
 * @funktionen MySQL Datenbank erstellen und auslesen, in ArrayList kopieren und weiter in Java verarbeiten.
 *
 */

public class Programm {

	public static void main(String[] args) {
		Datenbank db = new Datenbank();
		Scanner sc = new Scanner(System.in);
		ArrayList<Patient> pl = new ArrayList<Patient>();
		Wartezimmer wz = new Wartezimmer(pl);		
		db.verbindenDb();
		db.loeschenTabelleDb();
		db.erstellenTabelleDb();
		db.insertenValuesInTabelleDb();
		db.kopierenZeilenNachArrayList(pl);
		db.arrayListAusgeben(pl);	
		System.out.println("\n");
		wz.aufrufenPatient();
		wz.aufrufenPatient();
		wz.verlaesstPraxisPatient(pl.get(0));
		wz.aufrufenPatient();
		wz.aufrufenPatient();
		wz.aufrufenPatient();
		System.out.println("Geben Sie Ihre eigene SQL Abfrage ein (Bitte nur DQL Kommandos)\n");
		db.eigeneSqlEingebenDb(sc.nextLine());
		sc.close();
	};
}
