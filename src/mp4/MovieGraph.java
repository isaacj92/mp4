package mp4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

// TODO: Implement this class that represents an undirected graph with movies as vertices.
// The edges are weighted.
// This graph should be immutable except for the addition of vertices and edges. 
// It should not be possible to change a vertex after it has been added to the graph.

// You should indicate what the representation invariants and the abstraction function are for the representation you choose.

public class MovieGraph {

	public ArrayList<Vertex> vertices;
	public ArrayList<Edge> edges;
	private ArrayList<Rating> ratingList;
	
	
	public MovieGraph() throws IOException{
		ratingList = new ArrayList<Rating>();
		RatingIterator iter2 = new RatingIterator("data/u.data.txt");
		while ( iter2.hasNext() ) {
			Rating rating = iter2.getNext();
			ratingList.add(rating);
		}
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}
	
	/**
	 * Add a new movie to the graph. If the movie already exists in the graph
	 * then this method will return false. Otherwise this method will add the
	 * movie to the graph and return true.
	 * 
	 * @param movie
	 *            the movie to add to the graph. Requires that movie != null.
	 * @return true if the movie was successfully added and false otherwise.
	 * @throws IOException 
	 * @throws NoSuchMovieException 
	 * @modifies this by adding the movie to the graph if the movie did not
	 *           exist in the graph.
	 */
	public boolean addVertex(Movie movie) throws IOException, NoSuchMovieException {
		//if you've already added the vertex
		for(Vertex v : vertices){
			if(v.equals(new Vertex(movie))){
				return false;
			}
		}
		
		Vertex v = new Vertex(movie);
		vertices.add(v);
		if(vertices.size()>1){
			//ignore doing the edge with itself
			for(int i = 0; i < vertices.size()-1;i++){
				int edgeWeight = getEdgeWeight(vertices.get(i).getMovie(),movie);
				addEdge(vertices.get(i).getMovie(), movie, edgeWeight);
			}
		}
		
		return true;
	}

	/**
	 * Add a new edge to the graph. If the edge already exists in the graph then
	 * this method will return false. Otherwise this method will add the edge to
	 * the graph and return true.
	 * 
	 * @param movie1
	 *            one end of the edge being added. Requires that m1 != null.
	 * @param movie2
	 *            the other end of the edge being added. Requires that m2 !=
	 *            null. Also require that m1 is not equal to m2 because
	 *            self-loops are not meaningful in this graph.
	 * @param edgeWeight
	 *            the weight of the edge being added. Requires that edgeWeight >
	 *            0.
	 * @return true if the edge was successfully added and false otherwise.
	 * @throws NoSuchMovieException 
	 * @throws IOException 
	 * @modifies this by adding the edge to the graph if the edge did not exist
	 *           in the graph.
	 */
	public boolean addEdge(Movie movie1, Movie movie2, int edgeWeight) throws IOException, NoSuchMovieException {
		for(Edge e : edges){
			if(e.equals(new Edge(movie1, movie2, edgeWeight))){
				return false;
			}
		}
		edges.add(new Edge(movie1, movie2, edgeWeight));
		return true;
	}

	/**
	 * Add a new edge to the graph. If the edge already exists in the graph then
	 * this method should return false. Otherwise this method should add the
	 * edge to the graph and return true.
	 * 
	 * @param movieId1
	 *            the movie id for one end of the edge being added. Requires
	 *            that m1 != null.
	 * @param movieId2
	 *            the movie id for the other end of the edge being added.
	 *            Requires that m2 != null. Also require that m1 is not equal to
	 *            m2 because self-loops are not meaningful in this graph.
	 * @param edgeWeight
	 *            the weight of the edge being added. Requires that edgeWeight >
	 *            0.
	 * @return true if the edge was successfully added and false otherwise.
	 * @throws NoSuchMovieException 
	 * @throws IOException 
	 * @modifies this by adding the edge to the graph if the edge did not exist
	 *           in the graph.
	 */
	public boolean addEdge(int movieId1, int movieId2, int edgeWeight) throws IOException, NoSuchMovieException {
		Movie movie1ToPass = null;
		Movie movie2ToPass = null;
		for(Vertex v : vertices){
			if(v.getMovie().getID() == movieId1){
				movie1ToPass = v.getMovie();
			} else if(v.getMovie().getID() == movieId2){
				movie2ToPass = v.getMovie();
			}
			if(movie1ToPass != null && movie2ToPass != null){
				break;
			}
		}
		return addEdge(movie1ToPass, movie2ToPass, edgeWeight);
	}

	/**
	 * Return the length of the shortest path between two movies in the graph.
	 * Throws an exception if the movie ids do not represent valid vertices in
	 * the graph.
	 * 
	 * @param moviedId1
	 *            the id of the movie at one end of the path.
	 * @param moviedId2
	 *            the id of the movie at the other end of the path.
	 * @throws NoSuchMovieException
	 *             if one or both arguments are not vertices in the graph.
	 * @throws NoPathException
	 *             if there is no path between the two vertices in the graph.
	 * 
	 * @return the length of the shortest path between the two movies
	 *         represented by their movie ids.
	 * @throws IOException 
	 */
	public int getShortestPathLength(int moviedId1, int moviedId2)
			throws NoSuchMovieException, NoPathException, IOException {
		// TODO: Implement this method
				Vertex source = null;
				Vertex target = null;
				for(Vertex v : vertices){
					if(v.getID() == moviedId1){
						source = v;
					}
					if(v.getID() == moviedId2){
						target = v;
					}
					//break out of loop if both movies have been found
					if(source != null && target != null){
						break;
					}
				}
				System.out.println("Source Name: " + source.getMovie().getName());
				System.out.println("Target Name: " + target.getMovie().getName());
				
				Comparator<Vertex> comparator = new QueueComparer();
				Queue<Vertex> Q = new PriorityQueue<Vertex>(11,comparator);
				
				Vertex u = null;
				int alt = 1000000;
				
				source.minimumdistance = 0;
				Q.addAll(vertices);

				
				while(Q.isEmpty() == false){
					//Q will return a distance, so need the for loop to get the actual Vertex
					u = Q.poll();	
					if(u.equals(target)){
						break;
					}
					
					for(Vertex v : Q){
						alt = u.minimumdistance + this.getEdgeWeight(u.getMovie(), v.getMovie());
						if(alt < v.minimumdistance){
							v.minimumdistance = alt;
							v.previous = u;
						}
					}
					Vertex temp = Q.poll();
					Q.add(temp);
				}
				
				
				return target.minimumdistance;
	}

	/**
	 * Return the shortest path between two movies in the graph. Throws an
	 * exception if the movie ids do not represent valid vertices in the graph.
	 * 
	 * @param moviedId1
	 *            the id of the movie at one end of the path.
	 * @param moviedId2
	 *            the id of the movie at the other end of the path.
	 * @throws NoSuchMovieException
	 *             if one or both arguments are not vertices in the graph.
	 * @throws NoPathException
	 *             if there is no path between the two vertices in the graph.
	 * 
	 * @return the shortest path, as a list, between the two movies represented
	 *         by their movie ids. This path begins at the movie represented by
	 *         movieId1 and ends with the movie represented by movieId2.
	 * @throws IOException 
	 */
	public List<Movie> getShortestPath(int movieId1, int movieId2)
			throws NoSuchMovieException, NoPathException, IOException {
		// TODO: Implement this method
		Vertex source = null;
		Vertex target = null;
		for(Vertex v : vertices){
			if(v.getID() == movieId1){
				source = v;
			}
			if(v.getID() == movieId2){
				target = v;
			}
			//break out of loop if both movies have been found
			if(source != null && target != null){
				break;
			}
		}
		System.out.println("Source Name: " + source.getMovie().getName());
		System.out.println("Target Name: " + target.getMovie().getName());
		
		Comparator<Vertex> comparator = new QueueComparer();
		Queue<Vertex> Q = new PriorityQueue<Vertex>(11,comparator);
		
		Vertex u = null;
		int alt = 1000000;
		
		source.minimumdistance = 0;
		Q.addAll(vertices);

		
		while(Q.isEmpty() == false){
			//Q will return a distance, so need the for loop to get the actual Vertex
			u = Q.poll();
			if(u.equals(target)){
				break;
			}
			
			for(Vertex v : Q){
				alt = u.minimumdistance + this.getEdgeWeight(u.getMovie(), v.getMovie());
				if(alt < v.minimumdistance){
					v.minimumdistance = alt;
					v.previous = u;
				}
			}
			Vertex temp = Q.poll();
			Q.add(temp);
		}
		
		Stack<Movie> S = new Stack<Movie>();
		ArrayList<Movie> SMovie = new ArrayList<Movie>();
		u = target;
		S.add(u.getMovie());
		while(u.previous != null){
			S.add(u.previous.getMovie());
			u = u.previous;
		}
		
		while(S.isEmpty() == false){
			SMovie.add(S.pop());
		}

		return SMovie;
	}

	/**
	 * Returns the movie id given the name of the movie. For movies that are not
	 * in English, the name contains the English transliteration original name
	 * and the English translation. A match is found if any one of the two
	 * variations is provided as input. Typically the name string has <English
	 * Translation> (<English Transliteration>) for movies that are not in
	 * English.
	 * 
	 * @param name
	 *            the movie name for the movie whose id is needed.
	 * @return the id for the movie corresponding to the name. If an exact match
	 *         is not found then return the id for the movie with the best match
	 *         in terms of translation/transliteration of the movie name.
	 * @throws NoSuchMovieException
	 *             if the name does not match any movie in the graph.
	 */
	public int getMovieId(String name) throws NoSuchMovieException {
		for(Vertex v : vertices){
			if(v.getMovie().getName().equals(name)){
				return v.getMovie().getID();
			}
		}
		for(Vertex v : vertices){
			if(v.getMovie().getName().contains(name)){
				return v.getMovie().getID();
			}
		}
		throw new NoSuchMovieException();
	}
	
	/**
	 * overloaded method that calls the one below (this one takes moviews as arguments)
	 * @throws IOException 
	 */
	
	public int getEdgeWeight(Movie movie1, Movie movie2) throws IOException{
		return getEdgeWeight(movie1.getID(), movie2.getID());
	}
	
	/**
	 * This method is just used to get the EdgeWeight of 2 movies.
	 * @param movieID1 first movie to match
	 * @param movieID2 second movie to match
	 * @param ratingList list of ratings for all movies
	 * @return
	 * 		the EdgeWeight (1 + number of raters - intersection of likers + intersection of dislikers)
	 * 		will return 0 if movie1 or movie2 do not exist
	 * @throws IOException 
	 */
	
	public int getEdgeWeight(int movieID1, int movieID2) throws IOException{
		
		
		Map<Integer, Integer> ratingMapMovie1 = new TreeMap<Integer, Integer>();
		Map<Integer, Integer> ratingMapMovie2 = new TreeMap<Integer, Integer>();
		int likers = 0;
		int dislikers = 0;
		int raters = 0;
		//puts all ratings for both movies into a list
		for(Rating r : ratingList){
			if(r.getMovieId() == movieID1){
				ratingMapMovie1.put(r.getUserId(), r.getRating());
			} else if (r.getMovieId() == movieID2){
				ratingMapMovie2.put(r.getUserId(), r.getRating());
			}
		}
		for(Integer key : ratingMapMovie1.keySet()){
			//the same user has rated both movies
			if(ratingMapMovie2.get(key) != null){
				//if they liked both movies
				if(ratingMapMovie2.get(key) >= 4 && ratingMapMovie1.get(key) >= 4){
					likers++;
				}
				//if they disliked both movies
				if(ratingMapMovie2.get(key) <= 2 && ratingMapMovie1.get(key) <= 2){
					dislikers++;
				}
				raters++;
			}
		}
		
		return (1 + raters - likers - dislikers);
	}
	
	
	// Implement the next two methods for completeness of the MovieGraph ADT

	@Override
	public boolean equals(Object other) {
		if(this.hashCode() == ((MovieGraph) other).hashCode()){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		for(Edge e : edges){
			result = result * 31 + e.hashCode();
		}
		return result;
	}

}

