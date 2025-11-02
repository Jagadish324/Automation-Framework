//package com.arc.frameworkWeb.driverListener.interceptor;
//
//import com.arc.frameworkWeb.driverListener.SpyDriverListener;
//import com.google.common.collect.Lists;
//import org.mockito.stubbing.Answer;
//import org.openqa.selenium.WebDriver;
//
//import java.lang.reflect.Method;
//import java.util.List;
//
//public class DriverInterceptor extends BaseInterceptor implements Answer {
//
//    private static List<String> classesToBeProxied;
//
//    static {
//        classesToBeProxied = Lists.newArrayList(
//                "RemoteWebDriverOptions",
//                "RemoteWindow",
//                "RemoteTargetLocator",
//                "RemoteTimeouts",
//                "RemoteNavigation",
//                "RemoteInputMethodManager",
//                "RemoteAlert",
//                "RemoteLogs"
//        );
//    }
//
//    public DriverInterceptor(Object target, SpyDriverListener listener) {
//        this((WebDriver) target, target, listener);
//    }
//
//    public DriverInterceptor(WebDriver driver, Object target, SpyDriverListener listener) {
//        super(driver, target, listener, classesToBeProxied);
//    }
//
//    @Override
//    protected Boolean skipListenerNotification(Method method, Object[] args) {
//        return super.skipListenerNotification(method, args);
//    }
//}
