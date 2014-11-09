package mp4;

import java.io.*;

/**
 * This class helps one iterate over a file with information about movies drawn
 * from IMDb.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public class RatingIterator {

	private boolean next;
	private BufferedReader inputReader;
	private String nextRow;

	/**
	 * Create a MovieIterator given a filename.
	 * 
	 * @param fileName
	 *            the name of the file to open.
	 * @throws IOException
	 *             if there was a problem reading the file.
	 */
	public RatingIterator(String fileName) throws IOException {

		// open the file
		inputReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(fileName)));

		// read the first line and set the state of the iterator
		if ((nextRow = inputReader.readLine()) != null) {
			next = true;
		} else {
			next = false;
		}
	}

	/**
	 * Check if there is more data that the iterator can process.
	 * 
	 * @return true if there is more data otherwise return false.
	 */
	public boolean hasNext() {
		return next;
	}

	/**
	 * Return the next movie from the iterator.
	 * 
	 * @return the next movie rating in the list. Requires that hasNext() is
	 *         true otherwise null is returned.
	 */
	public Rating getNext() throws IOException {

		if (!next) {
			// we are the end of the stream so return null.
			// ideally the client should have checked hasNext() and this should
			// not be necessary
			return null;
		} else {

			// split the nextRow string at the tabs to create columns
			String[] columns = nextRow.split("\t");

			// the zeroth column represents the user id number
			int userId = Integer.parseInt(columns[0]);

			// the first column represents the movie id number
			int movieId = Integer.parseInt(columns[1]);

			// the second column represents the rating
			int rating = Integer.parseInt(columns[2]);

			// advance the iterator state wrt the input stream
			if ((nextRow = inputReader.readLine()) != null) {
				next = true;
			} else {
				next = false;
			}

			return new Rating(userId, movieId, rating);
		}
	}
}
