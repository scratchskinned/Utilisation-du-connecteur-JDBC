package tp2IngBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.*;
public class testmain {
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		
	
		addcommande(5,2,"2021-12-11");
		
		
	}
	
	
	
	
	
	
	
	
	
	static Connection ConnexionBase()
	{
	// connexion � la base de donn�e
	Connection con = null;
	// configure les parameters d�acc�s � MySQL
	String username = "root";
	String password = "root";
	// URL de connexion � MySQL server
	// Syntax: jdbc:TYPE:machine:port/DB_NAME
	// String url = &quot;jdbc:mysql://localhost:3306/mabase&quot;;
	String url = "jdbc:mysql://localhost:3306/mabase";
	// Etablir la connexion � la base � l�URL donn�e et avec
	// les param�tres de connexion username and password
	try
	{
	Class.forName("com.mysql.cj.jdbc.Driver");
	con = DriverManager.getConnection(url, username, password);
	System.out.println ("La connection avec la base de donn�e est �tablie");
	
	} catch (Exception e)
	// (ClassNotFoundException and SQLException)
	{
	System.out.print("D�sol� ! la connection � la base de donn�e n'est pas �tablie.");

	
	}
	return con;
	}
	
	static boolean getClientByID(int idClient){
		//D�terminer si un client existe dans la base ou non
		boolean registred = false ;
		// Initialisation de la connexion
		Connection con = ConnexionBase() ;
		// cr�er un objet &quot;Statement&quot; qui enverra des requ�tes SQL � la
		// base de donn�es
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requ�te de s�lection
		String selectQuery = "Select * from client where NumCli="+idClient+";";

		

		// execution de la requ�te et retour du resultat
		ResultSet results = null;
		results = stmt.executeQuery(selectQuery);
		if (results.next())
		{
		System.out.println("Client trouv�: " + results.getString("NomCli")+" Ville : "+ results.getString("Ville"));

		registred = true;
		}
		else
		{
		System.out.println("Client non trouv�");
		}
		stmt.close();
		// Fermeture de la connexion
		con.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return registred ;
		}
	
	static boolean getProduitByID(int idProduit){
		//D�terminer si un client existe dans la base ou non
		boolean registred = false ;
		// Initialisation de la connexion
		Connection con = ConnexionBase() ;
		// cr�er un objet &quot;Statement&quot; qui enverra des requ�tes SQL � la
		// base de donn�es
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requ�te de s�lection
		String selectQuery = "Select * from produit where NUMPRO="+idProduit+";";
		// execution de la requ�te et retour du resultat
		ResultSet results = null;
		results = stmt.executeQuery(selectQuery);
		if (results.next())
		{
		System.out.println("produit trouv�: " + results.getString("NOMPRO"));

		registred = true;
		}
		else
		{
		System.out.println("Produit non trouv�");
		}
		stmt.close();
		// Fermeture de la connexion
		con.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return registred ;
		}
	
	static boolean afficheProd(){
		//D�terminer si un client existe dans la base ou non
		boolean registred = false ;
		// Initialisation de la connexion
		Connection con = ConnexionBase() ;
		// cr�er un objet &quot;Statement&quot; qui enverra des requ�tes SQL � la
		// base de donn�es
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requ�te de s�lection
		
		for (int i = 1; i < 5; i++) {
			String selectQuery = "Select * from produit where NUMPRO="+i+";";
			ResultSet results = stmt.executeQuery(selectQuery);
			
			if (results.next()) {
				
				String prix = results.getString("PRIX");
				float f=Float.parseFloat(prix);
				int px = (int)f;
			
				if (px>10) {
				 
					System.out.println("le produit avec prix supp a 10: " +results.getString("NOMPRO"));
				}
				
				
			registred = true;
			}
			else
			{
			System.out.println("Produit non trouv�");
			}
		}
		
		
		// execution de la requ�te et retour du resultat
		
		
		stmt.close();
		// Fermeture de la connexion
		con.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return registred ;
		}
	
	static boolean getCommandeByID(int idcomm){
		//D�terminer si un client existe dans la base ou non
		boolean registred = false ;
		// Initialisation de la connexion
		Connection con = ConnexionBase() ;
		// cr�er un objet &quot;Statement&quot; qui enverra des requ�tes SQL � la
		// base de donn�es
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requ�te de s�lection
		String selectQuery = "Select * from COMMANDE where NUMCOM="+idcomm+";";
		// execution de la requ�te et retour du resultat
		ResultSet results = null;
		
		results = stmt.executeQuery(selectQuery);
		if (results.next())
		{
		System.out.println("Commande trouv�e: ");
		System.out.println("Date commande:"+results.getString("DATECOM"));
		System.out.println("Client qui a pass� la commande:"+results.getString("NUMCLI"));
		getdetailsByID(idcomm);
		registred = true;
		}
		else
		{
		System.out.println("Commande non trouv�e");
		}
		stmt.close();
		// Fermeture de la connexion
		con.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return registred ;
		}
	
	static boolean getdetailsByID(int idcomm){
		//D�terminer si un client existe dans la base ou non
		boolean registred = false ;
		// Initialisation de la connexion
		Connection con = ConnexionBase() ;
		// cr�er un objet &quot;Statement&quot; qui enverra des requ�tes SQL � la
		// base de donn�es
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requ�te de s�lection
		String selectQuery = "Select * from DETAIL where NUMCOM="+idcomm+";";
		// execution de la requ�te et retour du resultat
		ResultSet results = null;
		results = stmt.executeQuery(selectQuery);
		if (results.next())
		{
			System.out.println("D�tails de la commande: ");
			System.out.println("Num�ro Produit :"+results.getString("NUMPRO"));
			System.out.println("Quantit�:"+results.getString("QCOM"));
			
		registred = true;
		}
		else
		{
		System.out.println("Produit non trouv�");
		}
		stmt.close();
		// Fermeture de la connexion
		con.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return registred ;
		}
	
	
	static void addClient (int idClient, String nomCli, String ville, String categorie, int compte){
			
			// Initialisation de la connexion
			Connection con = ConnexionBase() ;
			// cr�er un objet &quot;Statement&quot; qui enverra des requ�tes SQL � la
			// base de donn�es
			Statement stmt;
			try {
			stmt = con.createStatement();
			// Initialiser votre requ�te d�insertion ou de mise � jour
			String insertString = "insert into client values ("+idClient+",'"+nomCli+"','"+ville+"','"+categorie+"',"+compte+")";

			
			// Execution de la requ�te
			int status = stmt.executeUpdate(insertString);
			if(status==0)
			System.out.println("Le client n'a pas �t� ajout�");
			else
			System.out.println("Le client a �t� ajout� avec succes");
			stmt.close();
			// Fermeture de la connexion
			con.close();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			}
	
	
	static void addproduit (int num, String nom, float prix, int qte){
		
		// Initialisation de la connexion
		Connection con = ConnexionBase() ;
		// cr�er un objet &quot;Statement&quot; qui enverra des requ�tes SQL � la
		// base de donn�es
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requ�te d�insertion ou de mise � jour
		String insertString = "insert into produit values ("+num+",'"+nom+"',"+prix+","+qte+")";

		
		// Execution de la requ�te
		int status = stmt.executeUpdate(insertString);
		if(status==0)
		System.out.println("Le produit n'a pas �t� ajout�");
		else
		System.out.println("Le produit a �t� ajout� avec succes");
		stmt.close();
		// Fermeture de la connexion
		con.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
	
static void addcommande (int num, int numcli, String date){
		
		// Initialisation de la connexion
		Connection con = ConnexionBase() ;
		// cr�er un objet &quot;Statement&quot; qui enverra des requ�tes SQL � la
		// base de donn�es
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requ�te d�insertion ou de mise � jour
		String insertString = "insert into commande values ("+num+","+numcli+",'"+date+"')";
 
		// Execution de la requ�te
		int status = stmt.executeUpdate(insertString);
		if(status==0)
		System.out.println("La commande n'a pas �t� ajout�");
		else
		System.out.println("Le commande a �t� ajout� avec succes");
		stmt.close();
		// Fermeture de la connexion
		con.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
