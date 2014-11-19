package mp4;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

// TODO: You should implement suitable JUnit tests to verify that your implementation of the MovieGraph class is correct.

public class MovieGraphTest {

	public static List<Movie> movieList;
	public static MovieGraph movieGraph, movieGraph2, movieGraph3;
	public static Movie ToyStory, GoldenEye, FourRooms, GetShorty, Copycat;

	@BeforeClass
	public static void setup() throws IOException{
		movieList = new ArrayList<Movie>();
		MovieIterator iter = new MovieIterator("data/u.item.txt");
		while ( iter.hasNext() ) {
			Movie movie = iter.getNext();
			movieList.add(movie);
		}
		movieGraph = new MovieGraph();
		movieGraph2 = new MovieGraph();
		movieGraph3 = new MovieGraph();
		ToyStory = movieList.get(0);
		GoldenEye = movieList.get(1);
		FourRooms = movieList.get(2);
		GetShorty = movieList.get(3);
		Copycat = movieList.get(4);
		
	}
	
	@Test
	public void testAddVertexAddEdge() throws IOException, NoSuchMovieException {
		assertTrue(movieGraph.addVertex(ToyStory));
		assertTrue(movieGraph.addVertex(GoldenEye));
		assertTrue(movieGraph.addVertex(FourRooms));
		assertTrue(movieGraph.addVertex(GetShorty));
		assertTrue(movieGraph.addVertex(Copycat));
		assertFalse(movieGraph.addVertex(ToyStory));
		assertFalse(movieGraph.addEdge(ToyStory, GoldenEye, 72));
	}
	
	
	@Test
	public void testGetMovieID() throws IOException, NoSuchMovieException{
		
		for(int i = 0; i < 6; i++){
			movieGraph2.addVertex(movieList.get(i));
		}
		assertEquals(movieGraph2.getMovieId("Toy Story"), 1);
		assertEquals(movieGraph2.getMovieId("Shanghai Triad"), 6);
		
	}
	
	@Test
	public void getShortestPath() throws IOException, NoSuchMovieException, NoPathException{
		
		for(int i = 0; i < 7; i++){
			movieGraph3.addVertex(movieList.get(i));
		}
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(i == 0 && j == 0){
					System.out.print("\t\t");
				} else if(i == 0 && j > 0){
					System.out.print(movieList.get(j-1).getName() + "\t");
				} else if(i > 0 && j == 0){
					System.out.print(movieList.get(i-1).getName() + "\t");
					if(i==4){
						System.out.print("\t");
					}
				} else if(i == j){
					System.out.print(0 + "\t\t");
				} else {
					System.out.print(movieGraph3.getEdgeWeight(movieList.get(j-1), movieList.get(i-1)) + "\t\t");
				}

			}
			System.out.println();
		}
		
		System.out.println("final: " + movieGraph3.getShortestPathLength(1,4));
		List<Movie> path = new ArrayList<Movie>();
		path = movieGraph3.getShortestPath(1, 4);
		for(Movie m : path){
			System.out.println(m.getName());
		}
		
	}

}
