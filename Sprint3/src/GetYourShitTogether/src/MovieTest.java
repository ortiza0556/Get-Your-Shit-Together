import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class MovieTest {

	Movie testFilm = new Movie("Detective Pikachu", "Mystery", "8", 2019, "Danny Devito plays the titular character Detective Pikachu in the most emotional film of the year",
												"Legendary Entertainment", "Rob Letterman");
	
	String directors = "Rob Letterman";
	String directors2 = "Anthony Russo, Joe Russo";
	
	@Test
	void testMovie() {
		Movie testFilm2 = new Movie("Avengers: Endgame", "Action", "9.5", 2019, "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble "
				+ "once more in order to undo Thanos' actions and restore order to the universe.", "Marvel Studios", "Anthony Russo, Joe Russo");
		assertEquals(testFilm2.getTitle(), "Avengers: Endgame");
		assertEquals(testFilm2.getGenre(), "Action");
		assertEquals(testFilm2.getRating(), "9.5");
		assertEquals(testFilm2.getReleaseDate(), 2019);
		assertEquals(testFilm2.getPlot(), "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. "
				+ "With the help of remaining allies, the Avengers assemble once more in order to undo Thanos' actions and restore order to the universe.");
		assertEquals(testFilm2.getID(), -1);
		assertEquals(testFilm2.getStudio(),"Marvel Studios");
		assertArrayEquals(testFilm2.getDirectors(), directors2.split(","));
	}

	@Test
	void testGetDirector() {
		assertArrayEquals(testFilm.getDirectors(), directors.split(","));
	}

	@Test
	void testGetTitle() {
		assertEquals(testFilm.getTitle(), "Detective Pikachu");
	}

	@Test
	void testGetGenre() {
		assertEquals(testFilm.getGenre(), "Mystery");
	}

	@Test
	void testGetRating() {
		assertEquals(testFilm.getRating(), "8");
	}

	@Test
	void testGetReleaseDate() {
		assertEquals(testFilm.getReleaseDate(), 2019);
	}

	@Test
	void testGetPlot() {
		assertEquals(testFilm.getPlot(), "Danny Devito plays the titular character Detective Pikachu in the most emotional film of the year");
	}

	@Test
	void testGetID() {
		assertEquals(testFilm.getID(), -1);
	}

	@Test
	void testGetStudio() {
		assertEquals(testFilm.getStudio(),"Legendary Entertainment");
	}

}
