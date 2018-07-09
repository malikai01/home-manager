package com.mlk.home.entity;

import com.mlk.home.base.BaseEntity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "take_names")
public class TakeNames extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String type;

    private String memo;

    @Column(name = "is_show")
    private String isShow;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "family_id")
    private Integer familyId;

    public TakeNames(Integer id, String name, String type, String memo, String isShow, Date createTime, Date updateTime, Integer familyId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.memo = memo;
        this.isShow = isShow;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.familyId = familyId;
    }

    public TakeNames() {
        super();
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * @return memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * @return is_show
     */
    public String getIsShow() {
        return isShow;
    }

    /**
     * @param isShow
     */
    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return family_id
     */
    public Integer getFamilyId() {
        return familyId;
    }

    /**
     * @param familyId
     */
    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }
}