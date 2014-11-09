package mp4;

public class Movie {
	private final int id;
	private final String name;
	private final int releaseYear;
	private final String imdbUrl;

	/**
	 * Create a new Movie object with the given information.
	 * 
	 * @param id
	 *            the movie id
	 * @param name
	 *            the name of the movie
	 * @param releaseYear
	 *            the year of the movie's release
	 * @param imdbUrl
	 *            the movie's IMDb URL
	 */
	public Movie(int id, String name, int releaseYear, String imdbUrl) {
		this.id = id;
		this.name = name;
		this.releaseYear = releaseYear;
		this.imdbUrl = imdbUrl;
	}

	/**
	 * Return the name of the movie
	 * 
	 * @return movie name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * hashCode for equality testing
	 */
	@Override
	public int hashCode() {
		return id;
	}
	
	/**
	 * Method to check if two Movie objects are equal
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		// TODO: Implement this method correctly.
		// TODO: Improve the specification for this method.
		return false; // this should be changed
	}
	
}
