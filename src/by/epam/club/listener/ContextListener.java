package by.epam.club.listener;

import by.epam.club.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * This listener gives a signal, when we start the program, for creating connection's pool
 *
 * @author Maeuski Igor
 * @version 1.0
 */

@WebListener
public class ContextListener implements ServletContextListener {
    /**
     * @param servletContextEvent listen start of the program for create pool of the connection
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance();
    }

    /**
     * @param servletContextEvent listen stop of the program for destroying pool of the connection
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().destroyConnectionPool();
    }
}
