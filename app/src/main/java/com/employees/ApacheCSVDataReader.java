package com.employees;

import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ApacheCSVDataReader implements CSVDataReader {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public DataReadResult readData(Reader reader, Consumer<Long> onInconsistentLine) throws IOException {
		DataReadResult res = new DataReadResult();

		Map<Long, Employee> employees = res.getEmployees();

		CSVFormat customFormat = CSVFormat.RFC4180.builder().setHeader(Headers.class).build();
		boolean headerLine = true;
		try (final CSVParser parser = CSVParser.parse(reader, customFormat);) {
			for (CSVRecord csvRecord : parser) {
				if (headerLine) {
					headerLine = false;
					continue;
				}
				if (!csvRecord.isConsistent()) {
					onInconsistentLine.accept(csvRecord.getRecordNumber() - 1);
					continue;
				}
				Date startDate;
				Date endDate;
				try {
					final String endDateString = csvRecord.get(Headers.END_DATE).trim();
					if (endDateString.toLowerCase().equals("null")) {
						endDate = Date.from(Instant.now());
					} else {
						endDate = DATE_FORMAT.parse(endDateString);
					}
					startDate = DATE_FORMAT.parse(csvRecord.get(Headers.START_DATE).trim());
				} catch (ParseException e) {
					onInconsistentLine.accept(csvRecord.getRecordNumber() - 1);
					continue;
				}
				
				if(startDate.compareTo(endDate) > 0)
				{
					// the start date is after the end date
					onInconsistentLine.accept(csvRecord.getRecordNumber() - 1);
					continue;
				}

				final Long employeeId = Long.parseLong(csvRecord.get(Headers.EMPOYEE_ID).trim());
				employees.putIfAbsent(employeeId, new Employee(employeeId));

				Employee currentEmployee = employees.get(employeeId);
				final Long projectId = Long.parseLong(csvRecord.get(Headers.PROJECT_ID).trim());
				final Project currentProject = new Project(projectId);
				currentEmployee.getProjectDays().putIfAbsent(currentProject, 0l);
				currentEmployee.getProjectDays().computeIfPresent(currentProject, (proj, currentDays) -> {
					final long days = Math.abs(Duration.between(startDate.toInstant(), endDate.toInstant()).toDays());
					return currentDays + days;
				});
			}
		}

		return res;
	}

}
