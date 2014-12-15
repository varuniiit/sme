package com.shashi.companyworkerspoc.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "WorkerDatabase";
	private List<NotificationDatabase> DemoORMLiteDao = null;
	private RuntimeExceptionDao<NotificationDatabase, Integer> DemoORMLiteRuntimeDao = null;

	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		// TODO Auto-generated method stub
		try {
			Log.i(DataBaseHelper.class.getName(), "onCreate");
			TableUtils
					.createTable(connectionSource, NotificationDatabase.class);
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(DataBaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		try {
			Log.i(DataBaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, NotificationDatabase.class,
					true);
			// after we drop the old databases, we create the new ones
			onCreate(arg0, connectionSource);
		} catch (SQLException e) {
			Log.e(DataBaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
	}

	public void insert(NotificationDatabase database) {
		RuntimeExceptionDao<NotificationDatabase, Integer> dao = getSimpleDataDao();
		dao.create(database);
	}

	public RuntimeExceptionDao<NotificationDatabase, Integer> getSimpleDataDao() {
		if (DemoORMLiteRuntimeDao == null) {
			DemoORMLiteRuntimeDao = getRuntimeExceptionDao(NotificationDatabase.class);
		}
		return DemoORMLiteRuntimeDao;
	}

	public List<NotificationDatabase> getAllEntries() {
		RuntimeExceptionDao<NotificationDatabase, Integer> dao = getSimpleDataDao();
		return dao.queryForAll();
	}

	public List<NotificationDatabase> getFalseStatus() {
		RuntimeExceptionDao<NotificationDatabase, Integer> dao = getSimpleDataDao();
		QueryBuilder<NotificationDatabase, Integer> queryBuilder = dao
				.queryBuilder();
		List<NotificationDatabase> list = new ArrayList<NotificationDatabase>();
		try {
			queryBuilder.where().eq("readStatus", "false");
			list = queryBuilder.query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void update(NotificationDatabase database) {
		RuntimeExceptionDao<NotificationDatabase, Integer> dao = getSimpleDataDao();
		UpdateBuilder<NotificationDatabase, Integer> updateBuilder = dao
				.updateBuilder();
		try {
			updateBuilder.updateColumnValue("readStatus", "false");
			updateBuilder.where().eq("id", database.getId());
			updateBuilder.update();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
