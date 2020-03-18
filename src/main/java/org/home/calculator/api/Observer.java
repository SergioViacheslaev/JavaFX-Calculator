package org.home.calculator.api;

/**
 * @author Sergei Viacheslaev
 */
public interface Observer {
    public void update(Observable o, Object arg);
}
