package uk.ac.ebi.pride.jaxb.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by IntelliJ IDEA.
 * User: rwang
 * Date: 20-Jul-2010
 * Time: 14:21:33
 */
public class BinaryDataUtils {
    public static Number[] toNumberArray(byte[] arr, CvTermReference dataType, ByteOrder order) {
        int numOfByte = getNumOfByte(dataType);
        int arrLength = arr.length;
        Number[] results = new Number[arrLength/numOfByte];
        ByteBuffer buffer = ByteBuffer.wrap(arr);
        buffer.order(order);
        for(int i = 0; i < arrLength; i+=numOfByte) {
            Number num;
            switch(dataType) {
                case INT_32_BIT   : num = buffer.getInt(i); break;
                case FLOAT_16_BIT : //ToDo: *** provide implementation here ; break;
                case FLOAT_32_BIT : num = buffer.getFloat(i); break;
                case INT_64_BIT   : num = buffer.getLong(i); break;
                case FLOAT_64_BIT : num = buffer.getDouble(i); break;
                default         : num = null;
            }
            results[i/numOfByte] = num;
        }

        return results;
    }

    public static double[] toDoubleArray(byte[] arr, CvTermReference dataType, ByteOrder order) {
        Number[] numArr = toNumberArray(arr, dataType, order);
        double[] doubleArr = new double[numArr.length];

        for(int i = 0; i < numArr.length; i++) {
            doubleArr[i] = numArr[i].doubleValue();
        }
        return doubleArr;
    }

    private static int getNumOfByte(CvTermReference dataType) {
        int numOfByte;

        switch(dataType) {
            case INT_32_BIT : numOfByte = 4; break;
            case FLOAT_16_BIT : numOfByte = 2 ; break;
            case FLOAT_32_BIT : numOfByte = 4; break;
            case INT_64_BIT   : numOfByte = 8; break;
            case FLOAT_64_BIT : numOfByte = 8; break;
            default : numOfByte = -1;
        }

        return numOfByte;
    }    
}
