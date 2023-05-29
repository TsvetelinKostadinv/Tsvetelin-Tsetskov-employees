package com.employees;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.Consumer;

public interface CSVDataReader {
	/**
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public DataReadResult readDataFromPath(String path, Consumer<Long> onInconsistentLine) throws FileNotFoundException, IOException;
}
