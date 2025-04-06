package com.zxb.backEnd.pojos.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactVO {
    /**
     * 联系人id
     */
    @TableField("contact")
    private int id;

    /**
     * 联系人用户名
     */
    private String username;

    /**
     * 与当前用户的房间号
     */
    private Integer roomId;

}
