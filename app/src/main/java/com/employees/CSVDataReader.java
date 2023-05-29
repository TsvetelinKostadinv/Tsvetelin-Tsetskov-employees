package com.employees;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CSVDataReader {
	/**
	 * 
	 * @param path
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void readDataFromPath(String path) throws FileNotFoundException, IOException;
}
