package com.wanggl.h2sql.base.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * @author : wh
 * description : 数字格式转化
 * @version : 1.0
 */
public class NumberFormatUtil {

    public static final String ZERO = "0";
    public static final String ONE_HUNDRED = "100";
    public static final BigDecimal BIG_DECIMAL_ONE_HUNDRED = new BigDecimal(ONE_HUNDRED);

    /**
     * 元转分，四舍五入
     *
     * @param yuan 元
     * @return 分
     */
    public static BigDecimal yuanToCentDecimal(String yuan) {
        if (null == yuan || StringUtil.isEmpty(yuan)) {
            return null;
        }
        return new BigDecimal(yuan)
                .multiply(BIG_DECIMAL_ONE_HUNDRED)
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 元转分，四舍五入
     *
     * @param yuan 元
     * @return 分
     */
    public static Long yuanToCentLong(String yuan) {
        BigDecimal bigDecimal = yuanToCentDecimal(yuan);
        if (null == bigDecimal) {
            return null;
        }
        return bigDecimal.longValue();
    }

    /**
     * 分转元，四舍五入
     *
     * @param cent 分
     * @return 元
     */
    public static String centToYuanRoundHalfUp(BigInteger cent) {
        if (null == cent) {
            return null;
        }
        String bigStr = ZERO;
        if (cent.compareTo(BigInteger.ZERO) != 0) {
            BigDecimal a = new BigDecimal(cent.toString());
            bigStr = a.divide(BIG_DECIMAL_ONE_HUNDRED, 2, RoundingMode.HALF_UP).toString();
        }
        return bigStr;
    }

    /**
     * 分转元，四舍五入
     *
     * @param cent 分
     * @return 元
     */
    public static String centToYuanRoundHalfUp(Long cent) {
        if (null == cent) {
            return null;
        }
        String bigStr = ZERO;
        if (cent.compareTo(0L) != 0) {
            BigDecimal a = new BigDecimal(cent);
            bigStr = a.divide(BIG_DECIMAL_ONE_HUNDRED, 2, RoundingMode.HALF_UP).toString();
        }
        return bigStr;
    }

    /**
     * 分转元，四舍五入
     *
     * @param cent 分
     * @return 元
     */
    public static String centToYuanRoundHalfUp(String cent) {
        if (null == cent) {
            return null;
        }
        String bigStr = ZERO;
        BigDecimal decimal = new BigDecimal(cent);
        if (decimal.compareTo(BigDecimal.ZERO) != 0) {
            bigStr = decimal.divide(BIG_DECIMAL_ONE_HUNDRED, 2, RoundingMode.HALF_UP).toString();
        }
        return bigStr;
    }
}
