import java.sql.*;
import java.util.*;

import sun.util.calendar.BaseCalendar.Date;
import treningsdagbok.ActiveDomainObject;

public class Treningsøkt extends ActiveDomainObject {
    private int øktid;
    private Date dato;
    private Time tidspunkt;
    private int varighet;
    private int form;
    private int prestasjon;
    private int løpenr;

    public Treningsøkt (int øktid) {
        this.øktid = øktid;
    }

    public int getØktid () {
        return øktid;
    }

    public void initialize (Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT dato, tidspunkt, varighet, form, prestasjon, løpenr " +
                 "FROM Treningsøkt WHERE øktid=" + øktid);
            while (rs.next()) {
                dato = rs.getDate("dato");
                tidspunkt = rs.getTime("tidspunkt");
                varighet = rs.getInt("varighet");
                form = rs.getInt("form");
                prestasjon = rs.getInt("prestasjon");
                løpenr = rs.getInt("løpenr");
            }
        } catch (Exception e) {
            System.out.println("DB-feil ved select av bruker = " + e);
            return;
        }
    }
    
    public void refresh (Connection conn) { 
        initialize(conn);
    }

    public void save (Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("UPDATE Treningsøkt set" +
            "dato="+dato+", tidspunkt="+tidspunkt+", varighet="+varighet+", form="+form+", prestasjon="+prestasjon+", løpenr=" +løpenr+
            " WHERE øktid="+øktid);
        } catch (Exception e) {
            System.out.println("DB-feil ved oppdatering av økt = "+e);
            return;
        }
    }

}