package dbunit;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dbUnitManager")
public class MysqlDbUnitManagerImpl extends DefaultDbUnitManagerImpl {

	private IDatabaseConnection dbconn;
	
	@Autowired
	public MysqlDbUnitManagerImpl(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	protected IDatabaseConnection getDbUnitConnection()
			throws DatabaseUnitException, SQLException {
		if (dbconn == null)
			dbconn = new DatabaseConnection(getConnection());
		dbconn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		return dbconn;
	}

}