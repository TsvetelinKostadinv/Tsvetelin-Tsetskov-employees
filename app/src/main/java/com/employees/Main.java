package com.employees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {

	private static final String HELP_STRING = "Start without parameters for GUI mode." + System.lineSeparator()
			+ "Start with one parameter(a path) to start in console mode";

	enum ExitCodes {
		SUCESS, UNRECOGNISED_PARAMS, FILE_NOT_FOUND, COULD_NOT_READ
	}

	enum Headers {
		EMPOYEE_ID, PROJECT_ID, START_DATE, END_DATE
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Should be GUI mode");
		} else if (args.length == 1) {
			CSVFormat customFormat = CSVFormat.RFC4180.builder().setHeader(Headers.class).build();

			try (final FileReader csvData = new FileReader(new File(args[0]));
					final CSVParser parser = CSVParser.parse(csvData, customFormat);) {
				for (CSVRecord csvRecord : parser) {
					if (!csvRecord.isConsistent()) {
						System.err.printf("Inconsistent row N%d - ignoring%n", csvRecord.getRecordNumber() - 1);
						continue;
					}
					csvRecord.get(Headers.EMPOYEE_ID);
				}
			} catch (FileNotFoundException e) {
				System.err.printf("File not found %s%n", args[0]);
				System.exit(ExitCodes.FILE_NOT_FOUND.ordinal());
			} catch (IOException e) {
				System.err.printf("Could not read file %s%n", args[0]);
				System.exit(ExitCodes.COULD_NOT_READ.ordinal());
			}

		} else {
			System.err.printf("Unrecognised number of parameters - %d%n", args.length);
			System.err.println(HELP_STRING);
			System.exit(ExitCodes.UNRECOGNISED_PARAMS.ordinal());
		}
	}
}
