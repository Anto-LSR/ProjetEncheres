package fr.eni.projet.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tools {

	public static String convertFormatDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(formatter);
	}
	
}
