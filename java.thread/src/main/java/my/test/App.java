package my.test;

import com.google.inject.Provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Provider<App> appProvider = new Provider<App>() {
            @Override
            public App get() {
                return new App();
            }
        };
        appProvider.get();


        Map<String, Object> map = new ConcurrentHashMap<>();
    }

}
