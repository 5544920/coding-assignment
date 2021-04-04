package com.assignment.tradingstoreservice.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TradingStoreValidationUtil {

	//MaturityDate Validation
	public static boolean isValidMaturityDate(String maturityDate) throws ParseException {
		SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
		Date maturityDateNew = sdformat.parse(maturityDate);
		Date currentDate = new Date();
		// System.out.println(maturityDateNew.before(currentDate));
		// System.out.println("maturityDateNew" + maturityDateNew);
		// System.out.println("currentDate" + currentDate);
		if (maturityDateNew.before(currentDate))
			return false;

		return true;

	}

}
