/*
 * Copyright (C) 2020 pierpaolo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package di.uniba.map.b.lab.rest.esercizi;

import di.uniba.map.b.lab.collection.esercizi.Articolo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author pierpaolo
 */
public class DBManagerSingleton {

    private static DBManagerSingleton instance;

    public static final String CREATE_TABLE_ITEM = "CREATE TABLE IF NOT EXISTS item (id VARCHAR(128) PRIMARY KEY, desc VARCHAR(2048), price DOUBLE, weight DOUBLE)";

    private Connection conn;

    private Properties dbprops;

    private DBManagerSingleton() {

    }

    public synchronized static DBManagerSingleton getInstance() {
        if (instance == null) {
            instance = new DBManagerSingleton();
            try {
                instance.connect();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return instance;
    }

    public void connect() throws SQLException {
        dbprops = new Properties();
        dbprops.setProperty("user", "user");
        dbprops.setProperty("password", "1234");
        conn = DriverManager.getConnection("jdbc:h2:./resources/db/store", dbprops);
        Statement stm = conn.createStatement();
        stm.executeUpdate(CREATE_TABLE_ITEM);
        stm.close();
    }

    private void reconnect() throws SQLException {
        if (conn != null && !conn.isValid(0)) {
            conn = DriverManager.getConnection("jdbc:h2:./resources/db/store", dbprops);
        }
    }

    public void insertArticolo(Articolo articolo) throws SQLException {
        reconnect();
        PreparedStatement pstm = conn.prepareStatement("INSERT INTO item VALUES (?,?,?,?)");
        pstm.setString(1, articolo.getId());
        pstm.setString(2, articolo.getDescrizione());
        pstm.setDouble(3, articolo.getCostoUnitario());
        pstm.setDouble(4, articolo.getPesoGrammi());
        pstm.executeUpdate();
        pstm.close();
    }

    public void removeArticolo(String id) throws SQLException {
        reconnect();
        PreparedStatement pstm = conn.prepareStatement("DELETE FROM item WHERE id=?");
        pstm.setString(1, id);
        pstm.executeUpdate();
        pstm.close();
    }

    public Articolo getArticolo(String id) throws SQLException {
        reconnect();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM item WHERE id=?");
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        Articolo a = null;
        if (rs.next()) {
            a = new Articolo(rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getDouble(4));
        }
        rs.close();
        stm.close();
        return a;
    }

}
