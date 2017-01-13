package de.lette.mensaplan.server;

/**
 * Diese Klasse stellt Methoden bereit um Nutzereingaben zu validieren.
 * 
 * @author Tommy Schmidt
 *
 */
public class Validator {
	/**
	 * Prüft ob ein String ein valides MySQL Date ist. Das Bedeutet der String ist so aufgebaut: YYYY-MM-DD
	 * 
	 * @param date
	 *            der zu validierende String
	 * @return true wenn der String ein valides MySQL Date ist, false wenn nicht
	 */
	@SuppressWarnings("unused")
	public static boolean validateDate(String date) {
		if(date != null && date.length() == 10) {
			String[] dateParts = date.split("-");
			// dateParts[0] -> "YYYY"
			// dateParts[1] -> "MM"
			// dateParts[2] -> "DD"
			if(dateParts.length == 3) {
				try {
					if(dateParts[0].length() == 4 && dateParts[1].length() == 2 && dateParts[2].length() == 2) {
						int year = Integer.parseInt(dateParts[0]);
						int month = Integer.parseInt(dateParts[1]);
						int day = Integer.parseInt(dateParts[2]);
						return true;
					}
				} catch(Exception ex) {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Prüft ob ein String eine valide MySQL DateTime ist. Das Bedeutet der String ist so aufgebaut: YYYY-MM-DD HH:MM:SS
	 * 
	 * @param dateTime
	 *            der zu validierende String
	 * @return wenn der String eine valide MySQL DateTime ist, false wenn nicht
	 */
	@SuppressWarnings("unused")
	public static boolean validateDateTime(String dateTime) {
		if(dateTime != null && dateTime.length() == 19) {
			String[] parts = dateTime.split(" ");
			if(parts.length == 2) {
				String[] dateParts = parts[0].split("-");
				// dateParts[0] -> "YYYY"
				// dateParts[1] -> "MM"
				// dateParts[2] -> "DD"
				if(dateParts.length == 3) {
					try {
						if(dateParts[0].length() == 4 && dateParts[1].length() == 2 && dateParts[2].length() == 2) {
							int year = Integer.parseInt(dateParts[0]);
							int month = Integer.parseInt(dateParts[1]);
							int day = Integer.parseInt(dateParts[2]);
							return true;
						}
					} catch(Exception ex) {
						return false;
					}
				}
				String[] timeParts = parts[1].split(":");
				// timeParts[0] -> "HH"
				// timeParts[1] -> "MM"
				// timeParts[2] -> "SS"
				if(timeParts.length == 3) {
					try {
						if(timeParts[0].length() == 2 && timeParts[1].length() == 2 && timeParts[2].length() == 2) {
							int hour = Integer.parseInt(timeParts[0]);
							int minute = Integer.parseInt(timeParts[1]);
							int second = Integer.parseInt(timeParts[2]);
							return true;
						}
					} catch(Exception ex) {
						return false;
					}
				}
			}
		}
		return false;
	}
}