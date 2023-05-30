package com.employees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.Consumer;

public interface CSVDataReader {

	enum Headers {
		EMPOYEE_ID, PROJECT_ID, START_DATE, END_DATE
	}

	public default DataReadResult readDataFromPath(String path, Consumer<Long> onInconsistentLine)
			throws FileNotFoundException, IOException {
		return readData(new FileReader(new File(path)), onInconsistentLine);
	}

	public DataReadResult readData(Reader reader, Consumer<Long> onInconsistentLine) throws IOException;
}
