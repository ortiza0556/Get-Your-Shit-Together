import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieBacklog <M extends Media>{
	
	private Connection conn;
	protected String filePath;
	
	public MovieBacklog () {
		connect();
		
	}
	
	protected void connect() {
		
		try {
			
			String database = "jdbc:sqlite:" + this.filePath;
			this.conn = DriverManager.getConnection(database);
			
			System.out.println("Connection made");
			
		} catch (SQLException e) {
			System.out.println("Failed to connect to databse:" + e.getMessage());
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
	
	public void Insert(Movie m, WatchableMediaStatus s, String userRating, int priority) {
		
		int id = m.getID();
		
		if(CheckIfExists(id) == 0) {
			
			String sql = "INSERT INTO Movies_Backlog (MovieID,Status,UserRating,Priority) "
                + "VALUES(?,?,?,?)";			
	        try {
	            PreparedStatement stmt = this.conn.prepareStatement(sql);
	            stmt.setInt(1,id);
	            
	            switch(s) {
	            	case TO_WATCH: stmt.setString(2, "To Watch"); 
	            	break;
	            	case WATCHING: stmt.setString(2, "Watching"); 
	            	break;
	            	case ON_HOLD: stmt.setString(2, "On Hold"); 
	            	break;
	            	case DROPPED: stmt.setString(2, "Dropped"); 
	            	break;
	            };
	            
	            stmt.setString(3, userRating);
	            stmt.setInt(4, priority);
	            
	            stmt.executeUpdate();
	            
	            System.out.println("Entry added.");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		}else {
			System.out.println("Entry already exists.");
		}
		
	}
	
	public void Delete(Movie m) {
		
		int id = m.getID();
		
		if(CheckIfExists(id) == 0) {
			
			String sql = "DELETE FROM Movies_Backlog WHERE MovieID = ?";
			
			
	        try {
	            PreparedStatement stmt = this.conn.prepareStatement(sql);
	            stmt.setInt(1,id);
	            
	            stmt.executeUpdate();
	            
	            System.out.println("Entry deleted.");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		}else {
			System.out.println("Entry does not exist.");
		}
		
	}

	public void Update(Movie m, String status, String userRating, int priority) {
		int id = m.getID();
		
		if(CheckIfExists(id) == 0) {
			
			String sql = "UPDATE Movies_Backlog SET Status = ?, UserRating = ?, Priority = ? WHERE MovieID = ?";
			
			
	        try {
	            PreparedStatement stmt = this.conn.prepareStatement(sql);

	            stmt.setString(1,status);
	            stmt.setString(2,userRating);
	            stmt.setInt(3,priority);
	            stmt.setInt(4,id);
	            stmt.executeUpdate();
	            
	            System.out.println("Entry updated.");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		}else {
			System.out.println("Entry does not exist.");
		}
		
	}
	
	private int CheckIfExists (int id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM Movies_Backlog WHERE MovieID = ? LIMIT 1)";
        
        try {
        	PreparedStatement stmt = this.conn.prepareStatement(sql);
        	stmt.setInt(1,id);
        	
            ResultSet rs = stmt.executeQuery(sql);
            
            int result= rs.getInt(0);

            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return 1;
        
    }
}