package helpers;

import exceptions.FatalException;
import org.apache.commons.lang3.StringUtils;

public class Printer {

    public void printBoard(String rawString) {

        if (StringUtils.length(rawString) != 17) {
            throw new FatalException("Invalid Result - Printer Cannot print");
        }

        String firstRow = rawString.substring(0, 5);
        String secondRow =  rawString.substring(6, 11);
        String thirdRow = rawString.substring(12, 17);

        System.out.println(firstRow);
        System.out.println("-----");
        System.out.println(secondRow);
        System.out.println("-----");
        System.out.println(thirdRow);
    }

    public void announceResult(String result) {
        System.out.println(result);
    }
}
