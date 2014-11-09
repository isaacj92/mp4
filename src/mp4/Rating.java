package mp4;

public class Rating {
	private final int userId;
	private final int movieId;
	private final int rating;

	/**
	 * Create a new Movie object with the given information.
	 * 
	 * @param userId
	 *            the id of the user that provided the rating
	 * @param movieId
	 *            the id of the movie that was rated
	 * @param rating
	 *            the rating provided by the user for the movie (1-5)
	 */
	public Rating(int userId, int movieId, int rating) {
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
	}

	/**
	 * Return the id of the movie this rating pertains to
	 * 
	 * @return movie id
	 */
	public int getMovieId() {
		return movieId;
	}

	/**
	 * Return the id of the user that provided the review
	 * 
	 * @return the user id of the user that provided the review
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Return the actual numeric rating for this Rating object
	 * 
	 * @return rating provided by this.userId for this.movieId
	 */
	public int getRating() {
		return rating;
	}
}
