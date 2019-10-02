package nodomain.team3point1.suhosim.entities;
import android.os.Build;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import nodomain.team3point1.suhosim.entities.ActivityDescTagLink;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ACTIVITY_DESC_TAG_LINK".
*/
public class ActivityDescTagLinkDao extends AbstractDao<ActivityDescTagLink, Long> {

    public static final String TABLENAME = "ACTIVITY_DESC_TAG_LINK";

    /**
     * Properties of entity ActivityDescTagLink.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ActivityDescriptionId = new Property(1, long.class, "activityDescriptionId", false, "ACTIVITY_DESCRIPTION_ID");
        public final static Property TagId = new Property(2, long.class, "tagId", false, "TAG_ID");
    };


    public ActivityDescTagLinkDao(DaoConfig config) {
        super(config);
    }
    
    public ActivityDescTagLinkDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ACTIVITY_DESC_TAG_LINK\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ACTIVITY_DESCRIPTION_ID\" INTEGER NOT NULL ," + // 1: activityDescriptionId
                "\"TAG_ID\" INTEGER NOT NULL );"); // 2: tagId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ACTIVITY_DESC_TAG_LINK\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ActivityDescTagLink entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getActivityDescriptionId());
        stmt.bindLong(3, entity.getTagId());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ActivityDescTagLink readEntity(Cursor cursor, int offset) {
        ActivityDescTagLink entity = new ActivityDescTagLink( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // activityDescriptionId
            cursor.getLong(offset + 2) // tagId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ActivityDescTagLink entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setActivityDescriptionId(cursor.getLong(offset + 1));
        entity.setTagId(cursor.getLong(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ActivityDescTagLink entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ActivityDescTagLink entity) {
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
