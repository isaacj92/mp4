package mp4;

import java.util.List;

// TODO: Implement this class that represents an undirected graph with movies as vertices.
// The edges are weighted.
// This graph should be immutable except for the addition of vertices and edges. 
// It should not be possible to change a vertex after it has been added to the graph.

// You should indicate what the representation invariants and the abstraction function are for the representation you choose.

public class MovieGraph {

	/**
	 * Add a new movie to the graph. If the movie already exists in the graph
	 * then this method should return false. Otherwise this method should add
	 * the movie to the graph and return true.
	 * 
	 * @param movie
	 *            the movie to add to the graph. Requires that movie != null.
	 * @return true if the movie was successfully added and false otherwise.
	 * @modifies this by adding the movie to the graph if the movie did not
	 *           exist in the graph.
	 */
	public boolean addVertex(Movie movie) {
		// TODO: Implement this method
		return false;
	}

	/**
	 * Add a new edge to the graph. If the edge already exists in the graph then
	 * this method should return false. Otherwise this method should add the
	 * edge to the graph and return true.
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
	 * @modifies this by adding the edge to the graph if the edge did not exist
	 *           in the graph.
	 */
	public boolean addEdge(Movie movie1, Movie movie2, int edgeWeight) {
		// TODO: Implement this method
		return false;
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
	 * @modifies this by adding the edge to the graph if the edge did not exist
	 *           in the graph.
	 */
	public boolean addEdge(int movieId1, int movieId2, int edgeWeight) {
		// TODO: Implement this method
		return false;
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
	 * @throws IllegalArgumentException
	 *             if one or both arguments are not vertices in the graph.
	 * 
	 * @return the length of the shortest path between the two movies
	 *         represented by their movie ids.
	 */
	public int getShortestPathLength(int moviedId1, int moviedId2)
			throws IllegalArgumentException {
		// TODO: Implement this method
		return 0;
	}

	/**
	 * Return the shortest path between two movies in the graph. Throws an
	 * exception if the movie ids do not represent valid vertices in the graph.
	 * 
	 * @param moviedId1
	 *            the id of the movie at one end of the path.
	 * @param moviedId2
	 *            the id of the movie at the other end of the path.
	 * @throws IllegalArgumentException
	 *             if one or both arguments are not vertices in the graph.
	 * 
	 * @return the shortest path, as a list, between the two movies represented
	 *         by their movie ids. This path begins at the movie represented by
	 *         movieId1 and ends with the movie represented by movieId2.
	 */
	public List<Movie> getShortestPath(int movieId1, int movieId2) {
		// TODO: Implement this method
		return null;
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
		// TODO: Implement this method
		return 0;
	}

	// Implement the next two methods for completeness of the MovieGraph ADT
	
	@Override
	public boolean equals(Object other) {
		// TODO: Implement this
		return false;
	}
	
	@Override
	public int hashCode() {
		// TODO: Implement a reasonable hash code method
		return 42;
	}
	
}
