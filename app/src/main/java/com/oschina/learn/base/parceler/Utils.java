package com.oschina.learn.base.parceler;

@SuppressWarnings("WeakerAccess")
public class Utils {

    /**
     * pass a class name to check if is a filter name
     * @param clzName class name
     * @return if the class has a same prefix with {@link Constants#FILTER_PREFIX}
     */
    public static boolean isFilterClass (String clzName) {
        if (clzName == null || clzName.length() == 0) return false;

        for (String prefix : Constants.FILTER_PREFIX) {
            if (clzName.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check out if it is a same class with {@link Object}
     * @param clzName class name
     * @return true if it is a {@link Object}
     */
    public static boolean isObjectClass (String clzName) {
        return Object.class.getName().equals(clzName);
    }

    /**
     * Check out if it is a super class of child.
     * @param child child class type
     * @param sup the super class name.
     * @return true if it is super class or itself
     */
    public static boolean isSuperClass (Class child, String sup) {
        return child != null && (child.getCanonicalName().equals(sup) || isSuperClass(child.getSuperclass(), sup));
    }

    /**
     * Check out if it is a super interface of child
     * @param child child class type
     * @param sup the super interface class name
     * @return true if it is super interface or itself
     */
    public static boolean isSuperInterface (Class child, String sup) {
        if (child == null) return false;
        if (child.getCanonicalName().equals(sup)) return true;

        Class[] interfaces = child.getInterfaces();
        for (Class in : interfaces) {
            if (in.getCanonicalName().equals(sup)) {
                return true;
            }
        }
        return false;
    }

}
