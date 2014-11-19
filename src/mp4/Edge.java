package mp4;

import java.io.IOException;
import java.util.ArrayList;

public class Edge {

	private Movie movie1;
	private Movie movie2;
	private int edgeWeight;
	
	public Edge(Movie movie1, Movie movie2, int edgeWeight) throws IOException, NoSuchMovieException{
		ArrayList<Movie> listMovies = new ArrayList<Movie>();
		MovieIterator iter = new MovieIterator("data/u.item.txt");
		while ( iter.hasNext() ) {
			listMovies.add(iter.getNext());
		}
		if(listMovies.contains(movie1) && listMovies.contains(movie2)){
			this.movie1 = movie1;
			this.movie2 = movie2;
			this.edgeWeight = edgeWeight;
		} else {
			throw new NoSuchMovieException();
		}
		
	}
	
	public ArrayList<Movie> getMovies(){
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList.add(movie1);
		movieList.add(movie2);
		return movieList;
		
	}
	
	public int getEdgeWeight(){
		int edgeWeight2 = edgeWeight;
		return edgeWeight2;
	}
	
	@Override
	public boolean equals(Object o){
		if(this.hashCode() == ((Edge) o).hashCode()){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		int result = 17;
		result = result * 31 + movie1.hashCode();
		result = result * 31 + movie2.hashCode();
		result = result * 31 + edgeWeight;
		return result;
	}
	
}
