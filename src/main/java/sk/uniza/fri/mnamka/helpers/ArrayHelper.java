package sk.uniza.fri.mnamka.helpers;

import java.util.Arrays;

public class ArrayHelper {

    @SafeVarargs
    public static <T> T[] mergeArrays(T[]... arrays) {
        return Arrays.stream(arrays)
                .flatMap(Arrays::stream)
                .toArray(size -> Arrays.copyOf(arrays[0], size));
    }

}
