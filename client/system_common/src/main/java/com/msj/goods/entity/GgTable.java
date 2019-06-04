package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 广告图片
 * </p>
 *
 * @author sun li
 * @since 2018-11-06
 */
public class GgTable extends Model<GgTable> {

    private static final long serialVersionUID = 1L;

    /**
     * 广告id
     */
    @TableId(value = "g_id", type = IdType.AUTO)
    private Integer gId;

    /**
     * 广告名称
     */
    private String gName;

    /**
     * 广告图片
     */
    private String gImgs;

    /**
     * 备注
     */
    private String remark;

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }
    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }
    public String getgImgs() {
        return gImgs;
    }

    public void setgImgs(String gImgs) {
        this.gImgs = gImgs;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.gId;
    }

    @Override
    public String toString() {
        return "GgTable{" +
        "gId=" + gId +
        ", gName=" + gName +
        ", gImgs=" + gImgs +
        ", remark=" + remark +
        "}";
    }
}
