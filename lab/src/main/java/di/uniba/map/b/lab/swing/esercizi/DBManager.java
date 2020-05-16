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
package di.uniba.map.b.lab.swing.esercizi;

import di.uniba.map.b.lab.collection.esercizi.Articolo;
import di.uniba.map.b.lab.collection.esercizi.Utente;
import di.uniba.map.b.lab.collection.esercizi.UtentePrime;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author pierpaolo
 */
public class DBManager {

    public static final String CREATE_TABLE_ITEM = "CREATE TABLE IF NOT EXISTS item (id VARCHAR(128) PRIMARY KEY, desc VARCHAR(2048), price DOUBLE, weight DOUBLE)";

    public static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS user (id VARCHAR(64) PRIMARY KEY, username VARCHAR(128), prime BOOLEAN)";

    private Connection conn;

    private Properties dbprops;

    public void connect() throws SQLException {
        dbprops = new Properties();
        dbprops.setProperty("user", "user");
        dbprops.setProperty("password", "1234");
        conn = DriverManager.getConnection("jdbc:h2:./resources/db/store", dbprops);
        Statement stm = conn.createStatement();
        stm.executeUpdate(CREATE_TABLE_ITEM);
        stm.executeUpdate(CREATE_TABLE_USER);
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

    public void insertUtente(Utente utente) throws SQLException {
        reconnect();
        PreparedStatement pstm = conn.prepareStatement("INSERT INTO user VALUES (?,?,?)");
        pstm.setString(1, utente.getId());
        pstm.setString(2, utente.getUsername());
        if (utente instanceof UtentePrime) {
            pstm.setBoolean(3, true);
        } else {
            pstm.setBoolean(3, false);
        }
        pstm.executeUpdate();
        pstm.close();
    }

    public List<Articolo> searchArticolo(String query) throws SQLException {
        reconnect();
        List<Articolo> r = new ArrayList<>();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM item WHERE desc LIKE '%" + query + "%'");
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Articolo a = new Articolo(rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getDouble(4));
            r.add(a);
        }
        rs.close();
        stm.close();
        return r;
    }

}
