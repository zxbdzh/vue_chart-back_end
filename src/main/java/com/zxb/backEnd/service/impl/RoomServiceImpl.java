package com.zxb.backEnd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxb.backEnd.mapper.RoomMapper;
import com.zxb.backEnd.pojos.Room;
import com.zxb.backEnd.service.RoomService;
import com.zxb.backEnd.utils.UserContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zxb
 * @description 针对表【room(联系人与联系人之间的房间)】的数据库操作Service实现
 * @createDate 2025-04-05 21:58:08
 */
@Service
@Transactional
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room>
        implements RoomService {

    /**
     * 根据用户id查询房间id
     *
     * @param id
     * @return
     */
    public Integer findByContactId(Integer id) {
        Room room = query()
                .eq("contact_id", id)
                .eq("user_id", UserContext.getUser().getId())
                .or()
                .eq("contact_id", UserContext.getUser().getId())
                .eq("user_id", id)
                .one(); // 先获取查询结果

        return room != null ? room.getRoomId() : null; // 添加空值判断
    }

    public void createRoom(Integer id) {
        Room room = new Room();
        room.setUserId(UserContext.getUser().getId());
        room.setContactId(id);
        save(room);
    }

    public void deleteRoom(Integer id) {
        if (id != null && query().eq("room_id", id).exists()) remove(new QueryWrapper<Room>().eq("room_id", id));
    }
}




