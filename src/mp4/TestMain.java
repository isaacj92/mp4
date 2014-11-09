package mp4;

import java.io.IOException;

// This is just for illustrating the use of the two iterators provided.

public class TestMain {

	/**
	 * @param args no arguments needed.
	 */
	public static void main(String[] args) throws IOException {
		
		System.out.println("The list of movies");
		MovieIterator iter = new MovieIterator("data/u.item.txt");
		while ( iter.hasNext() ) {
			Movie movie = iter.getNext();
			System.out.println(movie.getName());
		}
		
		System.out.println("----------------");
		
		System.out.println("The list of ratings");
		RatingIterator iter2 = new RatingIterator("data/u.data.txt");
		
		while (iter2.hasNext()) {
			Rating rating = iter2.getNext();
			System.out.println(rating.getMovieId() + " " + rating.getUserId() + " " + rating.getRating());
		}
	}

}
