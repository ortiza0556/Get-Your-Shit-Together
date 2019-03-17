import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VideoGameDatabase extends Database<VideoGame> {
	private Connection conn;

	public VideoGameDatabase () {
		
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
	protected void Insert(VideoGame v) {
		String title = v.getTitle();
		int release = v.getReleaseDate();
		String genre = v.getGenre();
		String rating = v.getRating();
		String plot = v.getPlot();
		String prodStudio = v.getStudio();
		
		String sql = "INSERT INTO VideoGames (Title,Release,Genre,Rating,Plot,ProductionStudio) "
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,title);
            stmt.setInt(2, release);
            stmt.setString(3, genre);
            stmt.setString(4, rating);
            stmt.setString(5, plot);
            stmt.setString(6, prodStudio);
            
            stmt.executeUpdate();
            
            System.out.println("Entry added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
		
	}

	@Override
	public ResultSet Search (String title) {
        String sql = "SELECT VGID,Title,Release,Genre,Rating,Plot,ProductionStudio FROM VideoGames WHERE "
                + "Title LIKE '%" + title + "%'";
        
        try {
            Statement stmt = this.conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                
            	System.out.println("ID:" + rs.getString("VGID"));
                System.out.println("Title:" + rs.getString("Title"));
                System.out.println("Release:" + rs.getInt("Release"));
                System.out.println("Genre:" + rs.getString("Genre"));
                System.out.println("Rating:" + rs.getString("Rating"));
                System.out.println("Plot:" + rs.getString("Plot"));
                System.out.println("ProductionStudio:" + rs.getString("ProductionStudio"));
            }
            
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
        
    }
}
