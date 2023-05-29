package com.employees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.employees.Main.Headers;

public class ApacheCSVDataReader implements CSVDataReader {

	@Override
	public void readDataFromPath(String path) throws FileNotFoundException, IOException {
		CSVFormat customFormat = CSVFormat.RFC4180.builder().setHeader(Headers.class).build();
		try (final FileReader csvData = new FileReader(new File(path));
				final CSVParser parser = CSVParser.parse(csvData, customFormat);) {
			for (CSVRecord csvRecord : parser) {
				if (!csvRecord.isConsistent()) {
					System.err.printf("Inconsistent row N%d - ignoring%n", csvRecord.getRecordNumber() - 1);
					continue;
				}
				csvRecord.get(Headers.EMPOYEE_ID);
			}
		} 

	}

}
