package com.zxb.backEnd.service;

import com.zxb.backEnd.pojos.Room;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zxb
* @description 针对表【room(联系人与联系人之间的房间)】的数据库操作Service
* @createDate 2025-04-05 21:58:08
*/
public interface RoomService extends IService<Room> {

    /**
     * 根据用户id查询房间id
     * @param id
     * @return
     */
    Integer findByContactId(Integer id);
}
