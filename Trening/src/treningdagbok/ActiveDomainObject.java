package treningdagbok;


import java.sql.*;

public abstract class ActiveDomainObject {
	public abstract void initialize( Connection conn);
	public abstract void refresh( Connection conn);
	public abstract void save( Connection conn);
	public abstract String toString();
}


