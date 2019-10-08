package nodomain.team3point1.suhosim.entities;
import android.os.Build;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import nodomain.team3point1.suhosim.entities.NotificationFilter;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NOTIFICATION_FILTER".
*/
public class NotificationFilterDao extends AbstractDao<NotificationFilter, Long> {

    public static final String TABLENAME = "NOTIFICATION_FILTER";

    /**
     * Properties of entity NotificationFilter.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property AppIdentifier = new Property(0, String.class, "appIdentifier", false, "APP_IDENTIFIER");
        public final static Property Id = new Property(1, Long.class, "id", true, "_id");
        public final static Property NotificationFilterMode = new Property(2, int.class, "notificationFilterMode", false, "NOTIFICATION_FILTER_MODE");
        public final static Property NotificationFilterSubMode = new Property(3, int.class, "notificationFilterSubMode", false, "NOTIFICATION_FILTER_SUB_MODE");
    };


    public NotificationFilterDao(DaoConfig config) {
        super(config);
    }
    
    public NotificationFilterDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NOTIFICATION_FILTER\" (" + //
                "\"APP_IDENTIFIER\" TEXT NOT NULL ," + // 0: appIdentifier
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 1: id
                "\"NOTIFICATION_FILTER_MODE\" INTEGER NOT NULL ," + // 2: notificationFilterMode
                "\"NOTIFICATION_FILTER_SUB_MODE\" INTEGER NOT NULL );"); // 3: notificationFilterSubMode
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_NOTIFICATION_FILTER_APP_IDENTIFIER ON NOTIFICATION_FILTER" +
                " (\"APP_IDENTIFIER\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NOTIFICATION_FILTER\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, NotificationFilter entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getAppIdentifier());
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(2, id);
        }
        stmt.bindLong(3, entity.getNotificationFilterMode());
        stmt.bindLong(4, entity.getNotificationFilterSubMode());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1);
    }    

    /** @inheritdoc */
    @Override
    public NotificationFilter readEntity(Cursor cursor, int offset) {
        NotificationFilter entity = new NotificationFilter( //
            cursor.getString(offset + 0), // appIdentifier
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // id
            cursor.getInt(offset + 2), // notificationFilterMode
            cursor.getInt(offset + 3) // notificationFilterSubMode
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, NotificationFilter entity, int offset) {
        entity.setAppIdentifier(cursor.getString(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setNotificationFilterMode(cursor.getInt(offset + 2));
        entity.setNotificationFilterSubMode(cursor.getInt(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(NotificationFilter entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(NotificationFilter entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
