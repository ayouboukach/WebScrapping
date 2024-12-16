package com.mahakim.app.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;


public class StaticMethods {

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public boolean compareFields(Object obj1, Object obj2) {
        Field[] objects = obj1.getClass().getDeclaredFields();
        boolean compare = true;
        for (int i = 1; i < objects.length; i++) {
            try {
                Class<?> classeSpecifique = Class.forName(obj1.getClass().getName());
                try {
                    Method method = classeSpecifique
                            .getMethod("get" + objects[i].getName().substring(0, 1).toUpperCase()
                                    + objects[i].getName().substring(1, objects[i].getName().length()));
                    try {
                        Object objectleft = (Object) method.invoke(obj2);
                        Object objectright = (Object) method.invoke(obj1);
                        if ((objectleft != null && objectright != null && !objectleft.equals(objectright))
                                || ((objectleft != null && objectright == null)
                                || (objectleft == null && objectright != null)))
                            compare = false;
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException e) {
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (compare == false)
                break;
        }
        return compare;
    }
    
    /*public static String calculateHMAC(String data, String key)
    	    throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
    	{
    	    SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
    	    Mac mac = Mac.getInstance("HmacSHA256");
    	    mac.init(secretKeySpec);
    	    String hash = Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes()));
    	    return new String(Hex.encode(hash.getBytes()));
    	}*/
}
