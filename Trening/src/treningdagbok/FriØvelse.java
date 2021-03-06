package treningdagbok;

import java.sql.*;
import java.util.*;


import treningdagbok.ActiveDomainObject;

public class FriØvelse extends ActiveDomainObject {
    private int øvelsesid;
    private int øktid;
    private String resultat;

    public FriØvelse (int øvelsesid, int øktid) {
        this.øktid = øktid;
        this.øvelsesid = øvelsesid;
    }

    public int getØvelsesid() {
		return øvelsesid;
	}
	public void setØvelsesid(int øvelsesid) {
		this.øvelsesid = øvelsesid;
	}
	public int getØktid() {
		return øktid;
	}
	public void setØktid(int øktid) {
		this.øktid = øktid;
	}
	public String getResultat() {
		return resultat;
	}
	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public void initialize (Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT øvelsesid, øktid, resultat "+
                "FROM FriØvelse WHERE øktid="+øktid+" AND øvelsesid="+øvelsesid);
            while (rs.next()) {
                øktid = rs.getInt("øktid");
                øvelsesid = rs.getInt("øvelsesid");
            }
        } catch (Exception e) {
            throw new IllegalStateException("DB-feil ved select av FriØvelse = "+e);
        }
    }

    public void refresh (Connection conn) {
        initialize(conn);
    }

    public void save (Connection conn) {
    	String error = "";
        try {
			String resultatString = StaticMethods.toQuote(resultat);
            Statement stmt = conn.createStatement();
			try {
				stmt.executeUpdate("insert into FriØvelse values("+øvelsesid+","+øktid+","+resultatString+")");
				return;
        } catch (Exception e) {
        	error +=e;
        	stmt.executeUpdate("update FriØvelse set resultat="+resultatString+"where øktid="+øktid+" and øvelsesid="+øvelsesid);
        }
    }catch (Exception e ) {
    	throw new IllegalStateException("db error during update of FriØvelse\n\t\tinsert error: " + error + " \n\t\t\tupdate error: "+e);

	}
}

	@Override
	public String toString() {
		return "ØvelsesID: " + this.øvelsesid +"\nØktID: " + this.øktid + "\nresultat: " + this.resultat;
	}
}
