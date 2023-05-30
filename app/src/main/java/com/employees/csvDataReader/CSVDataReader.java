package com.employees.csvDataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.Consumer;

/**
 * This interface could be made even more abstract - in the sence that nothing
 * is stopping it from parsing other formats of data apart from CSV
 * 
 * @author Tsvetelin
 *
 */
public interface CSVDataReader {

	enum Headers {
		EMPOYEE_ID, PROJECT_ID, START_DATE, END_DATE
	}

	/**
	 * Convenience method for reading
	 * 
	 * @param path               - the path to the .csv file
	 * @param onInconsistentLine - this is the callback that will be called upon
	 *                           when a line does not conform to the specified
	 *                           headers, the end date is before the start date or
	 *                           any situation where the parsing can continue
	 * @return The parsed out employees with their project and working days
	 * @throws FileNotFoundException - if the file does not exist or cannot be found
	 *                               on the specified path
	 * @throws IOException           - if the reader throws the exception upon
	 *                               reading
	 */
	public default DataReadResult readDataFromPath(String path, Consumer<Long> onInconsistentLine)
			throws FileNotFoundException, IOException {
		return readData(new FileReader(new File(path)), onInconsistentLine);
	}

	/**
	 * Parse the employee data from the csv format in the given reader
	 * 
	 * @param reader             - the stream to be read from
	 * @param onInconsistentLine - this is the callback that will be called upon
	 *                           when a line does not conform to the specified
	 *                           headers, the end date is before the start date or
	 *                           any situation where the parsing can continue
	 * @return The parsed out employees with their project and working days
	 * @throws IOException - if the reader throws the exception upon reading
	 */
	public DataReadResult readData(Reader reader, Consumer<Long> onInconsistentLine) throws IOException;
}
