package com.del.keeper.core.utils;

import java.math.BigDecimal;

/**
 * @author liyi
 * @version 创建时间：2016年10月9日 上午9:57:21
 */

public class MathUtil {
	/**
	 * double保留位数，四舍五入
	 *
	 * @param value
	 * @param digit
	 * @return
	 */
	public static Double formatDouble(Double value, int digit) {
		if (Double.isInfinite(value) || Double.isNaN(value))
			return 0.00;
		BigDecimal b = new BigDecimal(value);
		return b.setScale(digit, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static String calRate(Double value) {
		double val = formatDouble(value * 100, 2);
		return val + "%";
	}
}
