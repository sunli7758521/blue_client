package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 积分申请方式
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public class IntegralSqfs extends Model<IntegralSqfs> {

    private static final long serialVersionUID = 1L;

    /**
     * 申请方式
     */
    @TableId(value = "sqfs_id", type = IdType.AUTO)
    private Integer sqfsId;

    /**
     * 申请方式名称
     */
    private String sqName;

    public Integer getSqfsId() {
        return sqfsId;
    }

    public void setSqfsId(Integer sqfsId) {
        this.sqfsId = sqfsId;
    }
    public String getSqName() {
        return sqName;
    }

    public void setSqName(String sqName) {
        this.sqName = sqName;
    }

    @Override
    protected Serializable pkVal() {
        return this.sqfsId;
    }

    @Override
    public String toString() {
        return "IntegralSqfs{" +
        "sqfsId=" + sqfsId +
        ", sqName=" + sqName +
        "}";
    }
}
