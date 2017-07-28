package com.gxtc.yyj.newyin.mvp.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Jam on 2017/7/28.
 */

@Entity
public class Explore {
    @Id(autoincrement = true)
    private Long _id;
    private String ad;
    private String advertises;
    private int hasUnread;
    private boolean hasVisible;
    private int interval;
    private long maxId;
    private long nextCursor;
    private long previousCursor;
    private long sinceId;
    private String status;
    private int totalNumber;
    private int uveBlank;

    @Generated(hash = 870226112)
    public Explore(Long _id, String ad, String advertises, int hasUnread,
                   boolean hasVisible, int interval, long maxId, long nextCursor,
                   long previousCursor, long sinceId, String status, int totalNumber,
                   int uveBlank) {
        this._id = _id;
        this.ad = ad;
        this.advertises = advertises;
        this.hasUnread = hasUnread;
        this.hasVisible = hasVisible;
        this.interval = interval;
        this.maxId = maxId;
        this.nextCursor = nextCursor;
        this.previousCursor = previousCursor;
        this.sinceId = sinceId;
        this.status = status;
        this.totalNumber = totalNumber;
        this.uveBlank = uveBlank;
    }

    @Generated(hash = 1788270388)
    public Explore() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getAd() {
        return this.ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAdvertises() {
        return this.advertises;
    }

    public void setAdvertises(String advertises) {
        this.advertises = advertises;
    }

    public int getHasUnread() {
        return this.hasUnread;
    }

    public void setHasUnread(int hasUnread) {
        this.hasUnread = hasUnread;
    }

    public boolean getHasVisible() {
        return this.hasVisible;
    }

    public void setHasVisible(boolean hasVisible) {
        this.hasVisible = hasVisible;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public long getMaxId() {
        return this.maxId;
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }

    public long getNextCursor() {
        return this.nextCursor;
    }

    public void setNextCursor(long nextCursor) {
        this.nextCursor = nextCursor;
    }

    public long getPreviousCursor() {
        return this.previousCursor;
    }

    public void setPreviousCursor(long previousCursor) {
        this.previousCursor = previousCursor;
    }

    public long getSinceId() {
        return this.sinceId;
    }

    public void setSinceId(long sinceId) {
        this.sinceId = sinceId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalNumber() {
        return this.totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getUveBlank() {
        return this.uveBlank;
    }

    public void setUveBlank(int uveBlank) {
        this.uveBlank = uveBlank;
    }


    public Explore(String ad, String advertises, int hasUnread, boolean hasVisible, int interval, long maxId, long nextCursor, long previousCursor, long sinceId, String status, int totalNumber, int uveBlank) {
        this.ad = ad;
        this.advertises = advertises;
        this.hasUnread = hasUnread;
        this.hasVisible = hasVisible;
        this.interval = interval;
        this.maxId = maxId;
        this.nextCursor = nextCursor;
        this.previousCursor = previousCursor;
        this.sinceId = sinceId;
        this.status = status;
        this.totalNumber = totalNumber;
        this.uveBlank = uveBlank;
    }
}
