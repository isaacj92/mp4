package mp4;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author isaacjacobsen
 *
 *This class is meant to construct a Vertex for a MovieGraph
 *
 */

public class Vertex {
	
	private Movie movie;
	public int minimumdistance = (int) Double.POSITIVE_INFINITY;
	public Vertex previous = null;
	
	public Vertex(Movie movie) throws IOException, NoSuchMovieException{
		ArrayList<Movie> listMovies = new ArrayList<Movie>();
		MovieIterator iter = new MovieIterator("data/u.item.txt");
		while ( iter.hasNext() ) {
			listMovies.add(iter.getNext());
		}
		
		for(Movie m : listMovies){
			if(m.equals(movie)){
				this.movie = movie;
				return;
			} else {
			}
		}
		throw new NoSuchMovieException();
		
	}
	
	public Movie getMovie(){
		return movie;
	}
	
	public int getID(){
		return movie.getID();
	}

	
	@Override
	public boolean equals(Object o){
		if(this.hashCode() == ((Vertex) o).hashCode()){
			return true;
		}
		return false;
		
	}
	
	@Override
	public int hashCode(){
		return movie.hashCode();
	}
	
}
