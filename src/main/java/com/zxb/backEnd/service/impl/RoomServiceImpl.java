package com.zxb.backEnd.service.impl;

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
        System.out.println(id);
        System.out.println(UserContext.getUser().getId());
        return query().eq("contact_id", id).eq("user_id", UserContext.getUser().getId()).or().eq("contact_id", UserContext.getUser().getId()).eq("user_id", id).one().getRoomId();
    }
}




