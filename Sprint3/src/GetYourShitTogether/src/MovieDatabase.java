import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieDatabase extends Database<Movie> {
		
	private Connection conn;
	public String resultText = null;
	
	public MovieDatabase () {
		
		super.filePath = filePath;
		connect();
		
	}
	
	protected void connect() {
		
		try {
			
			String database = "jdbc:sqlite:" + this.filePath;
			this.conn = DriverManager.getConnection(database);
			
			System.out.println("Connection made");
			
		} catch (SQLException e) {
			System.out.println("Failed to connect to database:" + e.getMessage());
		}
	}
	
	public void close() {
		try {
			this.conn.close();
			
			System.out.println("Connection closed");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	protected void Insert(Movie m) {
		
		connect();
		resultText = null;
		
		String title = m.getTitle();
		int release = m.getReleaseDate();
		String genre = m.getGenre();
		String rating = m.getRating();
		String director = m.getDirectors();
		
		String sql = "INSERT INTO Movies (Title,Release,Genre,Rating,Director) "
                + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,title);
            stmt.setInt(2, release);
            stmt.setString(3, genre);
            stmt.setString(4, rating);
            stmt.setString(5, director);
            
            stmt.executeUpdate();
            
            System.out.println("Entry added");
            resultText = "Movie successfully added";
            close();		
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            close();
        }
        
		
	}

	@Override
	public ObservableList<Movie> Search (String title) {
        connect();
		resultText = null;
		
		String sql = "SELECT MovieID,Title,Release,Genre,Rating,Plot,ProductionStudio,Director FROM Movies WHERE "
                + "Title LIKE '%" + title + "%'";
        
        ObservableList<Movie> result = FXCollections.observableArrayList();
        boolean added = false;
        
        try {
            Statement stmt = this.conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                Movie currMovie = new Movie(rs.getString("Title"), rs.getString("Genre"), rs.getString("Rating"), 
            			rs.getInt("Release"), rs.getInt("MovieID"), rs.getString("Director"));
            	added = result.add(currMovie);
            	
            	if(!added){
            		System.out.println("Error, movie not added properly");
            	}else {
            		added = false;
            	}
            }
            
            resultText = "Results found";
            close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            close();
        }
        
        return null;
        
    }
	
	public int getMaxID() {
		connect();
		int max = -1;
		
		String sql = "SELECT MAX(MovieID) FROM Movies";
		
		try {
			Statement stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			max = rs.getInt(1);
		} catch (SQLException e) {
			
			
		}
		close();
		return max;
	}
	
}

