package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
	
	private String issueDateFormated, dueDateFormated;
	
	public DateFormatter (LocalDate inputDate) {
		formatIssueDate(inputDate);
		formatDueDate(inputDate);		
	}
	
	private String formatIssueDate (LocalDate inputDate) {
		issueDateFormated = inputDate.format(DateTimeFormatter.ofPattern("dd" + " 'de' " + "MMMM" + " 'de' " + "yyyy"));
		return issueDateFormated;
	}
	
	private String formatDueDate (LocalDate inputDate) {
		LocalDate dueDate = inputDate.plusYears(1);
		dueDateFormated = dueDate.format(DateTimeFormatter.ofPattern("dd" + " 'de' " + "MMMM" + " 'de' " + "yyyy"));
		return dueDateFormated;
	}
	
	public String getIssueDateFormated() {
		return issueDateFormated;
	}

	public String getDueDateFormated() {
		return dueDateFormated;
	}


}
