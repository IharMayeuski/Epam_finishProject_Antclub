package by.epam.club.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoGeneral {
    private static final Logger LOGGER = LogManager.getLogger(DaoGeneral.class);

    public void close(ResultSet rs, PreparedStatement ps){
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.info("ResultSet can't close without exception");
        }
        close(ps);
    }

    public void close(PreparedStatement ps){
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            LOGGER.info("Prepare statement can't close without exception");
        }
    }
}
