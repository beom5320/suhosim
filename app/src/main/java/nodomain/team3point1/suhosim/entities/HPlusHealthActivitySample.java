package nodomain.team3point1.suhosim.entities;

import nodomain.team3point1.suhosim.entities.DaoSession;
import de.greenrobot.dao.DaoException;

import de.greenrobot.dao.AbstractDao;
import nodomain.team3point1.suhosim.devices.SampleProvider;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * This class represents a sample specific to the device. Values like activity kind or
 * intensity, are device specific. Normalized values can be retrieved through the
 * corresponding {@link SampleProvider}.
 */
public class HPlusHealthActivitySample extends AbstractActivitySample  implements java.io.Serializable {

    private int timestamp;
    private long deviceId;
    private long userId;
    private byte[] rawHPlusHealthData;
    private int rawKind;
    private int rawIntensity;
    private int steps;
    private int heartRate;
    private Integer distance;
    private Integer calories;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient HPlusHealthActivitySampleDao myDao;

    private Device device;
    private Long device__resolvedKey;

    private User user;
    private Long user__resolvedKey;


    public HPlusHealthActivitySample() {
    }

    public HPlusHealthActivitySample(int timestamp, long deviceId, int rawKind) {
        this.timestamp = timestamp;
        this.deviceId = deviceId;
        this.rawKind = rawKind;
    }

    public HPlusHealthActivitySample(int timestamp, long deviceId, long userId, byte[] rawHPlusHealthData, int rawKind, int rawIntensity, int steps, int heartRate, Integer distance, Integer calories) {
        this.timestamp = timestamp;
        this.deviceId = deviceId;
        this.userId = userId;
        this.rawHPlusHealthData = rawHPlusHealthData;
        this.rawKind = rawKind;
        this.rawIntensity = rawIntensity;
        this.steps = steps;
        this.heartRate = heartRate;
        this.distance = distance;
        this.calories = calories;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHPlusHealthActivitySampleDao() : null;
    }

    @Override
    public int getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public long getDeviceId() {
        return deviceId;
    }

    @Override
    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public byte[] getRawHPlusHealthData() {
        return rawHPlusHealthData;
    }

    public void setRawHPlusHealthData(byte[] rawHPlusHealthData) {
        this.rawHPlusHealthData = rawHPlusHealthData;
    }

    public int getRawKind() {
        return rawKind;
    }

    public void setRawKind(int rawKind) {
        this.rawKind = rawKind;
    }

    @Override
    public int getRawIntensity() {
        return rawIntensity;
    }

    @Override
    public void setRawIntensity(int rawIntensity) {
        this.rawIntensity = rawIntensity;
    }

    @Override
    public int getSteps() {
        return steps;
    }

    @Override
    public void setSteps(int steps) {
        this.steps = steps;
    }

    @Override
    public int getHeartRate() {
        return heartRate;
    }

    @Override
    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    /** To-one relationship, resolved on first access. */
    public Device getDevice() {
        long __key = this.deviceId;
        if (device__resolvedKey == null || !device__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DeviceDao targetDao = daoSession.getDeviceDao();
            Device deviceNew = targetDao.load(__key);
            synchronized (this) {
                device = deviceNew;
            	device__resolvedKey = __key;
            }
        }
        return device;
    }

    public void setDevice(Device device) {
        if (device == null) {
            throw new DaoException("To-one property 'deviceId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.device = device;
            deviceId = device.getId();
            device__resolvedKey = deviceId;
        }
    }

    /** To-one relationship, resolved on first access. */
    public User getUser() {
        long __key = this.userId;
        if (user__resolvedKey == null || !user__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User userNew = targetDao.load(__key);
            synchronized (this) {
                user = userNew;
            	user__resolvedKey = __key;
            }
        }
        return user;
    }

    public void setUser(User user) {
        if (user == null) {
            throw new DaoException("To-one property 'userId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.user = user;
            userId = user.getId();
            user__resolvedKey = userId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
