package org.oss.pdfreporter.android.sql;

import java.io.IOException;

import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.sql.IPreparedStatement;
import org.oss.pdfreporter.sql.SQLException;

import android.database.sqlite.SQLiteDatabase;

public class SQLiteConnection implements IConnection {

	private SQLiteDatabase	db;

	public SQLiteConnection(String filename) {
		db = SQLiteDatabase.openDatabase(filename, null, SQLiteDatabase.OPEN_READONLY| SQLiteDatabase.NO_LOCALIZED_COLLATORS);
	}
	
	@Override
	public void close() throws IOException {
		db.close();
	}

	@Override
	public IPreparedStatement prepareStatement(String query) throws SQLException {
		return new SQLitePreparedStatement(query, db);
	}

}
