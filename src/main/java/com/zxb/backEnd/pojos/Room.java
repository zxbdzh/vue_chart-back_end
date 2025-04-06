package com.zxb.backEnd.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 联系人与联系人之间的房间
 * @TableName room
 */
@TableName(value ="room")
@Data
public class Room {
    /**
     * 房间id
     */
    @TableId(type = IdType.AUTO)
    private Integer roomId;

    /**
     * 
     */
    private Integer contactId;

    /**
     * 用户id
     */
    private Integer userId;
}