package nodomain.team3point1.suhosim.entities;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import nodomain.team3point1.suhosim.entities.UserAttributes;
import nodomain.team3point1.suhosim.entities.User;
import nodomain.team3point1.suhosim.entities.DeviceAttributes;
import nodomain.team3point1.suhosim.entities.Device;
import nodomain.team3point1.suhosim.entities.Tag;
import nodomain.team3point1.suhosim.entities.ActivityDescription;
import nodomain.team3point1.suhosim.entities.ActivityDescTagLink;
import nodomain.team3point1.suhosim.entities.MiBandActivitySample;
import nodomain.team3point1.suhosim.entities.PebbleHealthActivitySample;
import nodomain.team3point1.suhosim.entities.PebbleHealthActivityOverlay;
import nodomain.team3point1.suhosim.entities.PebbleMisfitSample;
import nodomain.team3point1.suhosim.entities.PebbleMorpheuzSample;
import nodomain.team3point1.suhosim.entities.HPlusHealthActivityOverlay;
import nodomain.team3point1.suhosim.entities.HPlusHealthActivitySample;
import nodomain.team3point1.suhosim.entities.No1F1ActivitySample;
import nodomain.team3point1.suhosim.entities.XWatchActivitySample;
import nodomain.team3point1.suhosim.entities.ZeTimeActivitySample;
import nodomain.team3point1.suhosim.entities.ID115ActivitySample;
import nodomain.team3point1.suhosim.entities.CalendarSyncState;
import nodomain.team3point1.suhosim.entities.Alarm;
import nodomain.team3point1.suhosim.entities.NotificationFilter;
import nodomain.team3point1.suhosim.entities.NotificationFilterEntry;
import nodomain.team3point1.suhosim.entities.BaseActivitySummary;

import nodomain.team3point1.suhosim.entities.UserAttributesDao;
import nodomain.team3point1.suhosim.entities.UserDao;
import nodomain.team3point1.suhosim.entities.DeviceAttributesDao;
import nodomain.team3point1.suhosim.entities.DeviceDao;
import nodomain.team3point1.suhosim.entities.TagDao;
import nodomain.team3point1.suhosim.entities.ActivityDescriptionDao;
import nodomain.team3point1.suhosim.entities.ActivityDescTagLinkDao;
import nodomain.team3point1.suhosim.entities.MiBandActivitySampleDao;
import nodomain.team3point1.suhosim.entities.PebbleHealthActivitySampleDao;
import nodomain.team3point1.suhosim.entities.PebbleHealthActivityOverlayDao;
import nodomain.team3point1.suhosim.entities.PebbleMisfitSampleDao;
import nodomain.team3point1.suhosim.entities.PebbleMorpheuzSampleDao;
import nodomain.team3point1.suhosim.entities.HPlusHealthActivityOverlayDao;
import nodomain.team3point1.suhosim.entities.HPlusHealthActivitySampleDao;
import nodomain.team3point1.suhosim.entities.No1F1ActivitySampleDao;
import nodomain.team3point1.suhosim.entities.XWatchActivitySampleDao;
import nodomain.team3point1.suhosim.entities.ZeTimeActivitySampleDao;
import nodomain.team3point1.suhosim.entities.ID115ActivitySampleDao;
import nodomain.team3point1.suhosim.entities.CalendarSyncStateDao;
import nodomain.team3point1.suhosim.entities.AlarmDao;
import nodomain.team3point1.suhosim.entities.NotificationFilterDao;
import nodomain.team3point1.suhosim.entities.NotificationFilterEntryDao;
import nodomain.team3point1.suhosim.entities.BaseActivitySummaryDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userAttributesDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig deviceAttributesDaoConfig;
    private final DaoConfig deviceDaoConfig;
    private final DaoConfig tagDaoConfig;
    private final DaoConfig activityDescriptionDaoConfig;
    private final DaoConfig activityDescTagLinkDaoConfig;
    private final DaoConfig miBandActivitySampleDaoConfig;
    private final DaoConfig pebbleHealthActivitySampleDaoConfig;
    private final DaoConfig pebbleHealthActivityOverlayDaoConfig;
    private final DaoConfig pebbleMisfitSampleDaoConfig;
    private final DaoConfig pebbleMorpheuzSampleDaoConfig;
    private final DaoConfig hPlusHealthActivityOverlayDaoConfig;
    private final DaoConfig hPlusHealthActivitySampleDaoConfig;
    private final DaoConfig no1F1ActivitySampleDaoConfig;
    private final DaoConfig xWatchActivitySampleDaoConfig;
    private final DaoConfig zeTimeActivitySampleDaoConfig;
    private final DaoConfig iD115ActivitySampleDaoConfig;
    private final DaoConfig calendarSyncStateDaoConfig;
    private final DaoConfig alarmDaoConfig;
    private final DaoConfig notificationFilterDaoConfig;
    private final DaoConfig notificationFilterEntryDaoConfig;
    private final DaoConfig baseActivitySummaryDaoConfig;

    private final UserAttributesDao userAttributesDao;
    private final UserDao userDao;
    private final DeviceAttributesDao deviceAttributesDao;
    private final DeviceDao deviceDao;
    private final TagDao tagDao;
    private final ActivityDescriptionDao activityDescriptionDao;
    private final ActivityDescTagLinkDao activityDescTagLinkDao;
    private final MiBandActivitySampleDao miBandActivitySampleDao;
    private final PebbleHealthActivitySampleDao pebbleHealthActivitySampleDao;
    private final PebbleHealthActivityOverlayDao pebbleHealthActivityOverlayDao;
    private final PebbleMisfitSampleDao pebbleMisfitSampleDao;
    private final PebbleMorpheuzSampleDao pebbleMorpheuzSampleDao;
    private final HPlusHealthActivityOverlayDao hPlusHealthActivityOverlayDao;
    private final HPlusHealthActivitySampleDao hPlusHealthActivitySampleDao;
    private final No1F1ActivitySampleDao no1F1ActivitySampleDao;
    private final XWatchActivitySampleDao xWatchActivitySampleDao;
    private final ZeTimeActivitySampleDao zeTimeActivitySampleDao;
    private final ID115ActivitySampleDao iD115ActivitySampleDao;
    private final CalendarSyncStateDao calendarSyncStateDao;
    private final AlarmDao alarmDao;
    private final NotificationFilterDao notificationFilterDao;
    private final NotificationFilterEntryDao notificationFilterEntryDao;
    private final BaseActivitySummaryDao baseActivitySummaryDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userAttributesDaoConfig = daoConfigMap.get(UserAttributesDao.class).clone();
        userAttributesDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        deviceAttributesDaoConfig = daoConfigMap.get(DeviceAttributesDao.class).clone();
        deviceAttributesDaoConfig.initIdentityScope(type);

        deviceDaoConfig = daoConfigMap.get(DeviceDao.class).clone();
        deviceDaoConfig.initIdentityScope(type);

        tagDaoConfig = daoConfigMap.get(TagDao.class).clone();
        tagDaoConfig.initIdentityScope(type);

        activityDescriptionDaoConfig = daoConfigMap.get(ActivityDescriptionDao.class).clone();
        activityDescriptionDaoConfig.initIdentityScope(type);

        activityDescTagLinkDaoConfig = daoConfigMap.get(ActivityDescTagLinkDao.class).clone();
        activityDescTagLinkDaoConfig.initIdentityScope(type);

        miBandActivitySampleDaoConfig = daoConfigMap.get(MiBandActivitySampleDao.class).clone();
        miBandActivitySampleDaoConfig.initIdentityScope(type);

        pebbleHealthActivitySampleDaoConfig = daoConfigMap.get(PebbleHealthActivitySampleDao.class).clone();
        pebbleHealthActivitySampleDaoConfig.initIdentityScope(type);

        pebbleHealthActivityOverlayDaoConfig = daoConfigMap.get(PebbleHealthActivityOverlayDao.class).clone();
        pebbleHealthActivityOverlayDaoConfig.initIdentityScope(type);

        pebbleMisfitSampleDaoConfig = daoConfigMap.get(PebbleMisfitSampleDao.class).clone();
        pebbleMisfitSampleDaoConfig.initIdentityScope(type);

        pebbleMorpheuzSampleDaoConfig = daoConfigMap.get(PebbleMorpheuzSampleDao.class).clone();
        pebbleMorpheuzSampleDaoConfig.initIdentityScope(type);

        hPlusHealthActivityOverlayDaoConfig = daoConfigMap.get(HPlusHealthActivityOverlayDao.class).clone();
        hPlusHealthActivityOverlayDaoConfig.initIdentityScope(type);

        hPlusHealthActivitySampleDaoConfig = daoConfigMap.get(HPlusHealthActivitySampleDao.class).clone();
        hPlusHealthActivitySampleDaoConfig.initIdentityScope(type);

        no1F1ActivitySampleDaoConfig = daoConfigMap.get(No1F1ActivitySampleDao.class).clone();
        no1F1ActivitySampleDaoConfig.initIdentityScope(type);

        xWatchActivitySampleDaoConfig = daoConfigMap.get(XWatchActivitySampleDao.class).clone();
        xWatchActivitySampleDaoConfig.initIdentityScope(type);

        zeTimeActivitySampleDaoConfig = daoConfigMap.get(ZeTimeActivitySampleDao.class).clone();
        zeTimeActivitySampleDaoConfig.initIdentityScope(type);

        iD115ActivitySampleDaoConfig = daoConfigMap.get(ID115ActivitySampleDao.class).clone();
        iD115ActivitySampleDaoConfig.initIdentityScope(type);

        calendarSyncStateDaoConfig = daoConfigMap.get(CalendarSyncStateDao.class).clone();
        calendarSyncStateDaoConfig.initIdentityScope(type);

        alarmDaoConfig = daoConfigMap.get(AlarmDao.class).clone();
        alarmDaoConfig.initIdentityScope(type);

        notificationFilterDaoConfig = daoConfigMap.get(NotificationFilterDao.class).clone();
        notificationFilterDaoConfig.initIdentityScope(type);

        notificationFilterEntryDaoConfig = daoConfigMap.get(NotificationFilterEntryDao.class).clone();
        notificationFilterEntryDaoConfig.initIdentityScope(type);
        baseActivitySummaryDaoConfig = daoConfigMap.get(BaseActivitySummaryDao.class).clone();
        baseActivitySummaryDaoConfig.initIdentityScope(type);

        userAttributesDao = new UserAttributesDao(userAttributesDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        deviceAttributesDao = new DeviceAttributesDao(deviceAttributesDaoConfig, this);
        deviceDao = new DeviceDao(deviceDaoConfig, this);
        tagDao = new TagDao(tagDaoConfig, this);
        activityDescriptionDao = new ActivityDescriptionDao(activityDescriptionDaoConfig, this);
        activityDescTagLinkDao = new ActivityDescTagLinkDao(activityDescTagLinkDaoConfig, this);
        miBandActivitySampleDao = new MiBandActivitySampleDao(miBandActivitySampleDaoConfig, this);
        pebbleHealthActivitySampleDao = new PebbleHealthActivitySampleDao(pebbleHealthActivitySampleDaoConfig, this);
        pebbleHealthActivityOverlayDao = new PebbleHealthActivityOverlayDao(pebbleHealthActivityOverlayDaoConfig, this);
        pebbleMisfitSampleDao = new PebbleMisfitSampleDao(pebbleMisfitSampleDaoConfig, this);
        pebbleMorpheuzSampleDao = new PebbleMorpheuzSampleDao(pebbleMorpheuzSampleDaoConfig, this);
        hPlusHealthActivityOverlayDao = new HPlusHealthActivityOverlayDao(hPlusHealthActivityOverlayDaoConfig, this);
        hPlusHealthActivitySampleDao = new HPlusHealthActivitySampleDao(hPlusHealthActivitySampleDaoConfig, this);
        no1F1ActivitySampleDao = new No1F1ActivitySampleDao(no1F1ActivitySampleDaoConfig, this);
        xWatchActivitySampleDao = new XWatchActivitySampleDao(xWatchActivitySampleDaoConfig, this);
        zeTimeActivitySampleDao = new ZeTimeActivitySampleDao(zeTimeActivitySampleDaoConfig, this);
        iD115ActivitySampleDao = new ID115ActivitySampleDao(iD115ActivitySampleDaoConfig, this);
        calendarSyncStateDao = new CalendarSyncStateDao(calendarSyncStateDaoConfig, this);
        alarmDao = new AlarmDao(alarmDaoConfig, this);
        notificationFilterDao = new NotificationFilterDao(notificationFilterDaoConfig, this);
        notificationFilterEntryDao = new NotificationFilterEntryDao(notificationFilterEntryDaoConfig, this);
        baseActivitySummaryDao = new BaseActivitySummaryDao(baseActivitySummaryDaoConfig, this);

        registerDao(UserAttributes.class, userAttributesDao);
        registerDao(User.class, userDao);
        registerDao(DeviceAttributes.class, deviceAttributesDao);
        registerDao(Device.class, deviceDao);
        registerDao(Tag.class, tagDao);
        registerDao(ActivityDescription.class, activityDescriptionDao);
        registerDao(ActivityDescTagLink.class, activityDescTagLinkDao);
        registerDao(MiBandActivitySample.class, miBandActivitySampleDao);
        registerDao(PebbleHealthActivitySample.class, pebbleHealthActivitySampleDao);
        registerDao(PebbleHealthActivityOverlay.class, pebbleHealthActivityOverlayDao);
        registerDao(PebbleMisfitSample.class, pebbleMisfitSampleDao);
        registerDao(PebbleMorpheuzSample.class, pebbleMorpheuzSampleDao);
        registerDao(HPlusHealthActivityOverlay.class, hPlusHealthActivityOverlayDao);
        registerDao(HPlusHealthActivitySample.class, hPlusHealthActivitySampleDao);
        registerDao(No1F1ActivitySample.class, no1F1ActivitySampleDao);
        registerDao(XWatchActivitySample.class, xWatchActivitySampleDao);
        registerDao(ZeTimeActivitySample.class, zeTimeActivitySampleDao);
        registerDao(ID115ActivitySample.class, iD115ActivitySampleDao);
        registerDao(CalendarSyncState.class, calendarSyncStateDao);
        registerDao(Alarm.class, alarmDao);
        registerDao(NotificationFilter.class, notificationFilterDao);
        registerDao(NotificationFilterEntry.class, notificationFilterEntryDao);
        registerDao(BaseActivitySummary.class, baseActivitySummaryDao);
    }
    
    public void clear() {
        userAttributesDaoConfig.getIdentityScope().clear();
        userDaoConfig.getIdentityScope().clear();
        deviceAttributesDaoConfig.getIdentityScope().clear();
        deviceDaoConfig.getIdentityScope().clear();
        tagDaoConfig.getIdentityScope().clear();
        activityDescriptionDaoConfig.getIdentityScope().clear();
        activityDescTagLinkDaoConfig.getIdentityScope().clear();
        miBandActivitySampleDaoConfig.getIdentityScope().clear();
        pebbleHealthActivitySampleDaoConfig.getIdentityScope().clear();
        pebbleHealthActivityOverlayDaoConfig.getIdentityScope().clear();
        pebbleMisfitSampleDaoConfig.getIdentityScope().clear();
        pebbleMorpheuzSampleDaoConfig.getIdentityScope().clear();
        hPlusHealthActivityOverlayDaoConfig.getIdentityScope().clear();
        hPlusHealthActivitySampleDaoConfig.getIdentityScope().clear();
        no1F1ActivitySampleDaoConfig.getIdentityScope().clear();
        xWatchActivitySampleDaoConfig.getIdentityScope().clear();
        zeTimeActivitySampleDaoConfig.getIdentityScope().clear();
        iD115ActivitySampleDaoConfig.getIdentityScope().clear();
        calendarSyncStateDaoConfig.getIdentityScope().clear();
        alarmDaoConfig.getIdentityScope().clear();
        notificationFilterDaoConfig.getIdentityScope().clear();
        notificationFilterEntryDaoConfig.getIdentityScope().clear();
        baseActivitySummaryDaoConfig.getIdentityScope().clear();
    }

    public UserAttributesDao getUserAttributesDao() {
        return userAttributesDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public DeviceAttributesDao getDeviceAttributesDao() {
        return deviceAttributesDao;
    }

    public DeviceDao getDeviceDao() {
        return deviceDao;
    }

    public TagDao getTagDao() {
        return tagDao;
    }

    public ActivityDescriptionDao getActivityDescriptionDao() {
        return activityDescriptionDao;
    }

    public ActivityDescTagLinkDao getActivityDescTagLinkDao() {
        return activityDescTagLinkDao;
    }

    public MiBandActivitySampleDao getMiBandActivitySampleDao() {
        return miBandActivitySampleDao;
    }

    public PebbleHealthActivitySampleDao getPebbleHealthActivitySampleDao() {
        return pebbleHealthActivitySampleDao;
    }

    public PebbleHealthActivityOverlayDao getPebbleHealthActivityOverlayDao() {
        return pebbleHealthActivityOverlayDao;
    }

    public PebbleMisfitSampleDao getPebbleMisfitSampleDao() {
        return pebbleMisfitSampleDao;
    }

    public PebbleMorpheuzSampleDao getPebbleMorpheuzSampleDao() {
        return pebbleMorpheuzSampleDao;
    }

    public HPlusHealthActivityOverlayDao getHPlusHealthActivityOverlayDao() {
        return hPlusHealthActivityOverlayDao;
    }

    public HPlusHealthActivitySampleDao getHPlusHealthActivitySampleDao() {
        return hPlusHealthActivitySampleDao;
    }

    public No1F1ActivitySampleDao getNo1F1ActivitySampleDao() {
        return no1F1ActivitySampleDao;
    }

    public XWatchActivitySampleDao getXWatchActivitySampleDao() {
        return xWatchActivitySampleDao;
    }

    public ZeTimeActivitySampleDao getZeTimeActivitySampleDao() {
        return zeTimeActivitySampleDao;
    }

    public ID115ActivitySampleDao getID115ActivitySampleDao() {
        return iD115ActivitySampleDao;
    }

    public CalendarSyncStateDao getCalendarSyncStateDao() {
        return calendarSyncStateDao;
    }

    public AlarmDao getAlarmDao() {
        return alarmDao;
    }

    public NotificationFilterDao getNotificationFilterDao() {
        return notificationFilterDao;
    }

    public NotificationFilterEntryDao getNotificationFilterEntryDao() {
        return notificationFilterEntryDao;
    }

    public BaseActivitySummaryDao getBaseActivitySummaryDao() {
        return baseActivitySummaryDao;
    }

}
