//package com.arc.frameworkWeb.driverListener;
//
//import com.arc.frameworkWeb.driverListener.types.driver.DriverCommand;
//import com.arc.frameworkWeb.driverListener.types.driver.DriverCommandException;
//import com.arc.frameworkWeb.driverListener.types.driver.DriverCommandResult;
//import com.arc.frameworkWeb.driverListener.types.element.ElementCommand;
//import com.arc.frameworkWeb.driverListener.types.element.ElementCommandException;
//import com.arc.frameworkWeb.driverListener.types.element.ElementCommandResult;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DriverEventDispatcher implements SpyDriverListener {
//
//    private List<SpyDriverListener> listeners;
//
//    public DriverEventDispatcher() {
//        this.listeners = new ArrayList<>();
//    }
//
//    public void addListener(SpyDriverListener listener) {
//        this.listeners.add(listener);
//    }
//
//    @Override
//    public void beforeDriverCommandExecuted(DriverCommand command) {
//        listeners.forEach(l -> l.beforeDriverCommandExecuted(command));
//    }
//
//    @Override
//    public void afterDriverCommandExecuted(DriverCommandResult command) {
//        listeners.forEach(l -> l.afterDriverCommandExecuted(command));
//    }
//
//    @Override
//    public void onException(DriverCommandException command) {
//        listeners.forEach(l -> l.onException(command));
//    }
//
//    @Override
//    public void beforeElementCommandExecuted(ElementCommand command) {
//        listeners.forEach(l -> l.beforeElementCommandExecuted(command));
//    }
//
//    @Override
//    public void afterElementCommandExecuted(ElementCommandResult command) {
//        listeners.forEach(l -> l.afterElementCommandExecuted(command));
//    }
//
//    @Override
//    public void onException(ElementCommandException command) {
//        listeners.forEach(l -> l.onException(command));
//    }
//}
//
