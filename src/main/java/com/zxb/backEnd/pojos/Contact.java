package com.zxb.backEnd.pojos;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@TableName(value ="contact")
@Data
public class Contact {
    /**
     * 对应的联系人
     */
    private Integer contact;

    /**
     * 当前用户id
     */
    private Integer id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}