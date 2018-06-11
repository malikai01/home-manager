package com.mlk.home.common.utils;

/**
 * Created by malikai on 2018-6-11.
 */
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

final class Java8StreamPartitionExample {

    public static void main(String[] args) {
        final List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);

        System.out.println(partition(list, 2));  // [[1, 2], [3, 4], [5, 6], [7]]
        System.out.println(partition(list, 3));  // [[1, 2, 3], [4, 5, 6], [7]]
    }
/*Lists.partition(teacherIds, 200)
            .stream()
				.flatMap(ts-> getAvators(ts, size).stream())
            .collect(Collectors.toList());*/
    private static  <T> Collection<List<T>> partition(List<T> list, int size) {
        final AtomicInteger counter = new AtomicInteger(0);

        return list.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / size))
                .values();
    }
}
