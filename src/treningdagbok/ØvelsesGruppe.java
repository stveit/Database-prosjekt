package src.treningdagbok;
import java.sql.*;
import java.util.*;

public class ØvelsesGruppe extends ActiveDomainObject {
	private int gruppeid;
	private String beskrivelse;

	public ØvelsesGruppe(int gruppeid) {
		this.gruppeid = gruppeid;
	}

	public int getGruppeid() {
		return gruppeid;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public void initialize(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select gruppebeskrivelse from ØvelsesGruppe where gruppeid="+gruppeid);
			while(rs.next()) {
				beskrivelse = rs.getString("gruppebeskrivelse");
			}
		} catch ( Exception e) {
			System.out.println("db error during select of bruker= "+e);
			return;
		}

	}

	public void refresh(Connection conn) {
		initialize(conn);
	}

	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("update ØvelsesGruppe set gruppebeskrivelse="+beskrivelse+"where gruppeid="+gruppeid);
			} catch(Exception e) {
			System.out.println("db error during update of øvelsesgruppe="+e);
			return;
			}
	}

}




