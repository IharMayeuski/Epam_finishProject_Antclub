package by.epam.club.dao.impl;

import by.epam.club.dao.DaoGeneral;
import by.epam.club.dao.TypeNewsDao;
import by.epam.club.pool.ConnectionPool;
import by.epam.club.pool.ConnectionProxy;
import by.epam.club.entity.TypeNews;
import by.epam.club.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static by.epam.club.dao.impl.SqlQuery.*;
import static by.epam.club.dao.impl.Status.*;
import static by.epam.club.dao.impl.Status.DELETED;

public class TypeNewsDaoImpl implements TypeNewsDao {
    private PreparedStatement st;
    private ResultSet rs;
    private Connection con = null;
    private ConnectionPool connectionPool = null;
    private DaoGeneral daoGeneral = new DaoGeneral();

    @Override
    public Set<TypeNews> takeTypes() throws DaoException {
        Set<TypeNews> types = new HashSet<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            st = con.prepareStatement(TYPE_FIND_ALL_UNDELETED.getQuery());
            rs = st.executeQuery();

            while (rs.next()) {
                TypeNews typeData = createTypeData(rs);
                types.add(typeData);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            daoGeneral.close(rs,st);
            connectionPool.returnConnection(con);
        }
        return types;
    }

    private TypeNews createTypeData(ResultSet rs) throws DaoException {
        TypeNews typeNews = new TypeNews();
        try {
            typeNews.setId(rs.getInt(1));
            typeNews.setTypeNews(rs.getString(2));
            if (rs.getInt(3) == 0) {
                typeNews.setDeleted(UNDELETED.getStatus());
            } else {
                typeNews.setDeleted(DELETED.getStatus());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return typeNews;
    }
}
