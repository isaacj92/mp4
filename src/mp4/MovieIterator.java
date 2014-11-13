package mp4;

import java.io.*;

/**
 * This class helps one iterate over a file with information about movies drawn
 * from IMDb.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public class MovieIterator {

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
	public MovieIterator(String fileName) throws IOException {

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
	 * @return true if there is more data to process otherwise return false.
	 */
	public boolean hasNext() {
		return next;
	}

	/**
	 * Return the next movie from the iterator.
	 * 
	 * @return the next movie in the list. Requires that hasNext() is true
	 *         otherwise null is returned.
	 */
	public Movie getNext() throws IOException {

		if (!next) {
			// we are the end of the stream so return null.
			// ideally the client should have checked hasNext() and this should
			// not be necessary
			return null;
		} else {

			// we should first replace the | character with tab spacing to
			// simplify regex matching
			nextRow = nextRow.replace('|', '\t');

			// now split the string at the tabs to create columns
			String[] columns = nextRow.split("\t");

			// the zeroth column represents the movie id number
			int id = Integer.parseInt(columns[0]);

			// grab the name
			// most movies have the release year as part of the name so we strip
			// the year out. the year is enclosed within ( )
			String name;

			int idx1 = columns[1].lastIndexOf('(');

			if (idx1 == -1) {
				name = columns[1];
			} else {
				name = columns[1].substring(0, idx1 - 1);
			}

			int idx2 = columns[1].lastIndexOf(')');

			// grab the release year
			// some movies do not have this set correctly so we will use 1900 as
			// the default
			int releaseYear;

			try {

				releaseYear = Integer.parseInt(columns[1].substring(idx1 + 1,
						idx2));
			} catch (Exception e) {
				releaseYear = 1900; // default year if we do not have a good
									// match
			}

			// grab the IMDb URL
			String imdbUrl = columns[4];

			// advance the iterator state wrt the input stream
			if ((nextRow = inputReader.readLine()) != null) {
				next = true;
			} else {
				next = false;
			}

			return new Movie(id, name, releaseYear, imdbUrl);
		}
	}
}
