package de.comcave.maxron;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class Datenbank {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rset; 
	private String url;
	private String user;
	private String pw;
	private String query;
	
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public Statement getStmt() {
		return stmt;
	}
	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
	public ResultSet getRset() {
		return rset;
	}
	public void setRset(ResultSet rset) {
		this.rset = rset;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	// Parameter Konstruktor
	public Datenbank(String url, String user, String pw) {
		this.setUrl(url);
		this.setUser(user);
		this.setPw(pw);
		this.verbindenDb();
	};
	
	// Parameter Methode zum verbinden
	public void verbindenDb(String url, String user, String pw) {
		try {
		this.setConn(DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPw()));
		this.setStmt(this.conn.createStatement());
		System.out.println("DB Verbindung hergestellt.\n");
		} catch (SQLException sqlEx) {
			System.out.println(sqlEx.getMessage());
		}		
	};
	
	// Standard Konstruktor
	public Datenbank() {};
	
	// Standardmethode für Verbindung zur vordefinierten Datenbank
	public void verbindenDb() {
		this.setUrl("jdbc:mysql://127.0.0.1:3306/uebung");
		this.setUser("uebungsuser");
		this.setPw("geheim");
		try {
		this.setConn(DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPw()));
		this.setStmt(this.conn.createStatement());
		System.out.println("DB Verbindung hergestellt.\n");
		} catch (SQLException sqlEx) {
			System.out.println(sqlEx.getMessage());
		}		
	};
	
	// Eingabe und Rückgabe Methode
	public void eigeneSqlEingebenDb(String string) {
		query = string;
		try {
		stmt.execute(query);
		System.out.println(query + " wurde ausgeführt!\n");
//		query = "SELECT * FROM patient";
		rset = stmt.executeQuery(query);
	    ResultSetMetaData rsmd = rset.getMetaData();
	    System.out.println("Ausgabe:\n");
	    int columnsNr = rsmd.getColumnCount();
    	while (rset.next()) {
	        for (int i = 1; i <= columnsNr; i++) {
	            if (i > 1) System.out.print("-  ");
	            String columnVal = rset.getString(i);
	            System.out.print(columnVal + " " + "\n");
	            // + rsmd.getColumnName(i)
		       }
	    	}
		} catch (SQLException sqlEx) {
			System.out.println(sqlEx.getMessage());
		}
	};
	
	// Vordefinierte SQL Funktionen
	public void loeschenTabelleDb() { 
		query = "DROP TABLE IF EXISTS patient";
		try {
		stmt.execute(query);
		System.out.println("Table drop ausgeführt!\n");
		} catch (SQLException sqlEx) {
			System.out.println(sqlEx.getMessage());
		}	
	};
	
	// Vordefinierte SQL Funktionen
	public void erstellenTabelleDb() {
		query = "CREATE TABLE IF NOT EXISTS patient ("
			  + "id INT NOT NULL AUTO_INCREMENT,"
			  + "vorname VARCHAR(255),"
			  + "nachname VARCHAR(255),"
			  + "palter INT,"
			  + "krankheit VARCHAR(255),"
			  + "PRIMARY KEY (id)"
			  + ")";
		try {
			stmt.execute(query);
			System.out.println("Table create ausgeführt!\n");
		} catch (SQLException sqlEx) {
			System.out.println(sqlEx.getMessage());
		}
	};
	
	// Vordefinierte SQL Funktionen
	public void insertenValuesInTabelleDb() {
		String insert = "Insert ausgeführt!\n";
		try {
		query = "INSERT INTO patient (vorname, nachname, palter, krankheit)"
			  + "VALUES('Dolph', 'Wannsee', 34, 'Husten')";
			stmt.execute(query);
			System.out.println(insert);
		query = "INSERT INTO patient (vorname, nachname, palter, krankheit)"
			  + "VALUES('Hanuman', 'Schwarz', 33, 'Schnupfen')";
			stmt.execute(query);
			System.out.println(insert);
		query = "INSERT INTO patient (vorname, nachname, palter, krankheit)"
			  + "VALUES('Gina Sabrina', 'Komprende', 32, 'Migraene')";
			stmt.execute(query);
			System.out.println(insert);
		query = "INSERT INTO patient (vorname, nachname, palter, krankheit)"
			  + "VALUES('Muamar', 'Hannibal', 31, 'Muskelkater')";
			stmt.execute(query);
			System.out.println(insert);	
		query = "INSERT INTO patient (vorname, nachname, palter, krankheit)"
			  + "VALUES('Kevin', 'Haendler', 30, 'Entzuendung')";
			stmt.execute(query);
			System.out.println(insert);
		} catch (SQLException sqlEx) {
			System.out.println(sqlEx.getMessage());
		}
	};
	
	// Methode für ArrayList mit DB Zeilenwerten füllen
	public ArrayList<Patient> kopierenZeilenNachArrayList(ArrayList<Patient> arrayList) {
		query = "SELECT * FROM patient";
		try {
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				Patient patient = new Patient(rset.getString(2),
										      rset.getString(3),
										      rset.getInt(4),
										      rset.getString(5));
				arrayList.add(patient);
				System.out.println("Zeileneintrag erfolgreich auf ArrayList kopiert!\n");
			}	
		} catch (SQLException sqlEx) {
			System.out.println(sqlEx.getMessage());
		}
		return arrayList;
	};
	
	// Methode für ArrayList ausgeben
	public void arrayListAusgeben(ArrayList<Patient> arrayList) {
		System.out.println("Terminliste: \n");
		Iterator<Patient> iter = arrayList.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	};
}
