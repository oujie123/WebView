package com.gacrnd.gcs.base.autoservice;

import java.util.ServiceLoader;

/**
 * @author Jack_Ou  created on 2020/9/23.
 */
public final class MyServiceLoader {

    private MyServiceLoader() {
    }

    public static <S> S load(Class<S> service) {
        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }
}
