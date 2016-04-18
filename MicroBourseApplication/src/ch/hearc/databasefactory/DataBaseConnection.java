package ch.hearc.databasefactory;

import ch.hearc.exception.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author charlesombangndo
 */
public class DataBaseConnection {
    Connection connection;
    private static DataBaseConnection database;
    private static final String URL = "jdbc:derby://localhost:1527/microBourseDataBase";
    private static final String USERNAME = "microbourse";
    private static final String PASSWORD = "microbourse";
    
    /**
     * Connection à la base de données
     * @param url
     * @param username
     * @param password 
     */
    public DataBaseConnection(String url, String username, String password) throws DatabaseException{
        try{
            this.connection = DriverManager.getConnection(url, username, password);
            this.connection.setAutoCommit(false);
        }catch(SQLException ex){
            throw new DatabaseException("Connection à la base de données impossible!");
        }
    }
    
    /**
     * Connexion à l abase de données
     * @return
     * @throws DatabaseException 
     */
    public static DataBaseConnection getDataBase() throws DatabaseException{
        return getDatabase(URL, USERNAME, PASSWORD);
    }
    
    /**
     * Instanciation de la base de données
     * @param url
     * @param username
     * @param password
     * @return 
     */
    public static DataBaseConnection getDatabase(String url, String username, String password) throws DatabaseException{
        if (database == null) {
            database = new DataBaseConnection(url, username, password);
        }
        return database;
    }
    
    /**
     * Retourne une connexion
     * @return Connection
     */
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Validation des mises à jours
     */
    public void commit() throws DatabaseException{
        try {
            this.connection.commit();
        } catch (SQLException ex) {
            throw new DatabaseException(ex);//Throwable ex
        }
    }

    /**
     * Annulation des mises à jours
     */
    public void rollback() throws DatabaseException{
        try {
            this.connection.rollback();
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        }
    }
    
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (Throwable t) {
            }
        }
    }
}
