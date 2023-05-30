package com.employees;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	private static final String HELP_STRING = "Start without parameters for GUI mode." + System.lineSeparator()
			+ "Start with one parameter(a path) to start in console mode";

	enum ExitCodes {
		SUCESS, UNRECOGNISED_PARAMS, FILE_NOT_FOUND, COULD_NOT_READ
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Should be GUI mode");
		} else if (args.length == 1) {
			CSVDataReader reader = new ApacheCSVDataReader();

			try {
				DataReadResult res = reader.readDataFromPath(args[0], (Long row) -> {
					System.out.printf("Inconsistent line N%d - ignoring!%n", row);
				});
				
				
				
			} catch (FileNotFoundException e) {
				System.out.printf("File %s not found%n", args[0]);
				System.exit(ExitCodes.FILE_NOT_FOUND.ordinal());
			} catch (IOException e) {
				System.out.printf("Could not read file %s%n", args[0]);
				System.exit(ExitCodes.COULD_NOT_READ.ordinal());
			}

		} else {
			System.err.printf("Unrecognised number of parameters - %d%n", args.length);
			System.err.println(HELP_STRING);
			System.exit(ExitCodes.UNRECOGNISED_PARAMS.ordinal());
		}
	}
}
