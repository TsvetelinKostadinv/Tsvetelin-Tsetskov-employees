package com.employees;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.ToLongFunction;

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
			try {
				CSVDataReader reader = new ApacheCSVDataReader();
				DataReadResult res = reader.readDataFromPath(args[0], (Long row) -> {
					System.out.printf("Inconsistent line N%d - ignoring!%n", row);
				});
				System.out.println(res.getEmployees());

				Map<EmployeePair, Long> pairs = new HashMap<>();

				res.getEmployees().forEach((idA, emplA) -> {
					res.getEmployees().forEach((idB, emplB) -> {
						if (idA < idB) {
							pairs.compute(new EmployeePair(emplA, emplB), (emplPair, oldVal) -> {
								if (oldVal == null) {
									// the employee pair is not yet inserted
									// find common projects and add up the min of the days
									System.out.printf("Computing common hours between %d and %d%n", idA, idB);
									final long totalCommonWorkingDays = emplA.getProjectDays().entrySet().stream()
											.filter(entry -> emplB.getProjectDays().containsKey(entry.getKey()))
											.map(entry -> {
												final Long daysOfB = emplB.getProjectDays().get(entry.getKey());
												return Math.min(entry.getValue(), daysOfB);
											}).mapToLong(x -> x).sum();

									return totalCommonWorkingDays;
								} else {
									// The employees are already seen
									// But they should be contained in the maps only once
									// This should not happen
									throw new IllegalStateException("Every pair of employees should be unique");
								}
							});
						}
					});
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
