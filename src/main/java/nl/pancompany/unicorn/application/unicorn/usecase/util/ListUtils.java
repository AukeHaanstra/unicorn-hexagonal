package nl.pancompany.unicorn.application.unicorn.usecase.util;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListUtils {

    public static <T> long countDifferentFromMostFrequent(List<T> list) {
        if (list == null || list.isEmpty()) {
            return 0L;
        }
        T mostFrequent = findMostFrequentElement(list);
        return countElementsDifferentFromMostFrequentElement(list, mostFrequent);
    }

    private static <T> T findMostFrequentElement(List<T> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private static <T> long countElementsDifferentFromMostFrequentElement(List<T> list, T mostFrequent) {
        return list.stream().filter(e -> !e.equals(mostFrequent)).count();
    }

}
