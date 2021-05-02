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
	// connexion à la base de donnée
	Connection con = null;
	// configure les parameters d’accès à MySQL
	String username = "root";
	String password = "root";
	// URL de connexion à MySQL server
	// Syntax: jdbc:TYPE:machine:port/DB_NAME
	// String url = &quot;jdbc:mysql://localhost:3306/mabase&quot;;
	String url = "jdbc:mysql://localhost:3306/mabase";
	// Etablir la connexion à la base à l’URL donnée et avec
	// les paramètres de connexion username and password
	try
	{
	Class.forName("com.mysql.cj.jdbc.Driver");
	con = DriverManager.getConnection(url, username, password);
	System.out.println ("La connection avec la base de donnée est établie");
	
	} catch (Exception e)
	// (ClassNotFoundException and SQLException)
	{
	System.out.print("Désolé ! la connection à la base de donnée n'est pas établie.");

	
	}
	return con;
	}
	
	static boolean getClientByID(int idClient){
		//Déterminer si un client existe dans la base ou non
		boolean registred = false ;
		// Initialisation de la connexion
		Connection con = ConnexionBase() ;
		// créer un objet &quot;Statement&quot; qui enverra des requêtes SQL à la
		// base de données
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requête de sélection
		String selectQuery = "Select * from client where NumCli="+idClient+";";

		

		// execution de la requête et retour du resultat
		ResultSet results = null;
		results = stmt.executeQuery(selectQuery);
		if (results.next())
		{
		System.out.println("Client trouvé: " + results.getString("NomCli")+" Ville : "+ results.getString("Ville"));

		registred = true;
		}
		else
		{
		System.out.println("Client non trouvé");
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
		//Déterminer si un client existe dans la base ou non
		boolean registred = false ;
		// Initialisation de la connexion
		Connection con = ConnexionBase() ;
		// créer un objet &quot;Statement&quot; qui enverra des requêtes SQL à la
		// base de données
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requête de sélection
		String selectQuery = "Select * from produit where NUMPRO="+idProduit+";";
		// execution de la requête et retour du resultat
		ResultSet results = null;
		results = stmt.executeQuery(selectQuery);
		if (results.next())
		{
		System.out.println("produit trouvé: " + results.getString("NOMPRO"));

		registred = true;
		}
		else
		{
		System.out.println("Produit non trouvé");
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
		//Déterminer si un client existe dans la base ou non
		boolean registred = false ;
		// Initialisation de la connexion
		Connection con = ConnexionBase() ;
		// créer un objet &quot;Statement&quot; qui enverra des requêtes SQL à la
		// base de données
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requête de sélection
		
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
			System.out.println("Produit non trouvé");
			}
		}
		
		
		// execution de la requête et retour du resultat
		
		
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
		//Déterminer si un client existe dans la base ou non
		boolean registred = false ;
		// Initialisation de la connexion
		Connection con = ConnexionBase() ;
		// créer un objet &quot;Statement&quot; qui enverra des requêtes SQL à la
		// base de données
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requête de sélection
		String selectQuery = "Select * from COMMANDE where NUMCOM="+idcomm+";";
		// execution de la requête et retour du resultat
		ResultSet results = null;
		
		results = stmt.executeQuery(selectQuery);
		if (results.next())
		{
		System.out.println("Commande trouvée: ");
		System.out.println("Date commande:"+results.getString("DATECOM"));
		System.out.println("Client qui a passé la commande:"+results.getString("NUMCLI"));
		getdetailsByID(idcomm);
		registred = true;
		}
		else
		{
		System.out.println("Commande non trouvée");
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
		//Déterminer si un client existe dans la base ou non
		boolean registred = false ;
		// Initialisation de la connexion
		Connection con = ConnexionBase() ;
		// créer un objet &quot;Statement&quot; qui enverra des requêtes SQL à la
		// base de données
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requête de sélection
		String selectQuery = "Select * from DETAIL where NUMCOM="+idcomm+";";
		// execution de la requête et retour du resultat
		ResultSet results = null;
		results = stmt.executeQuery(selectQuery);
		if (results.next())
		{
			System.out.println("Détails de la commande: ");
			System.out.println("Numéro Produit :"+results.getString("NUMPRO"));
			System.out.println("Quantité:"+results.getString("QCOM"));
			
		registred = true;
		}
		else
		{
		System.out.println("Produit non trouvé");
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
			// créer un objet &quot;Statement&quot; qui enverra des requêtes SQL à la
			// base de données
			Statement stmt;
			try {
			stmt = con.createStatement();
			// Initialiser votre requête d’insertion ou de mise à jour
			String insertString = "insert into client values ("+idClient+",'"+nomCli+"','"+ville+"','"+categorie+"',"+compte+")";

			
			// Execution de la requête
			int status = stmt.executeUpdate(insertString);
			if(status==0)
			System.out.println("Le client n'a pas été ajouté");
			else
			System.out.println("Le client a été ajouté avec succes");
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
		// créer un objet &quot;Statement&quot; qui enverra des requêtes SQL à la
		// base de données
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requête d’insertion ou de mise à jour
		String insertString = "insert into produit values ("+num+",'"+nom+"',"+prix+","+qte+")";

		
		// Execution de la requête
		int status = stmt.executeUpdate(insertString);
		if(status==0)
		System.out.println("Le produit n'a pas été ajouté");
		else
		System.out.println("Le produit a été ajouté avec succes");
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
		// créer un objet &quot;Statement&quot; qui enverra des requêtes SQL à la
		// base de données
		Statement stmt;
		try {
		stmt = con.createStatement();
		// Initialiser votre requête d’insertion ou de mise à jour
		String insertString = "insert into commande values ("+num+","+numcli+",'"+date+"')";
 
		// Execution de la requête
		int status = stmt.executeUpdate(insertString);
		if(status==0)
		System.out.println("La commande n'a pas été ajouté");
		else
		System.out.println("Le commande a été ajouté avec succes");
		stmt.close();
		// Fermeture de la connexion
		con.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
