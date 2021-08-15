package com.openld.toolkit;

import java.util.Collection;

/**
 * author: lllddd
 * created on: 2021/8/10 9:54
 * description:
 */
public class CollectionUtils {
    private CollectionUtils() {

    }

    /**
     * 集合判空
     *
     * @param collection 集合对象
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 数组判空
     *
     * @param objects 数组对象
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }
}
