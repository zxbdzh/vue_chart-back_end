package com.zxb.backEnd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxb.backEnd.mapper.ContactMapper;
import com.zxb.backEnd.pojos.Contact;
import com.zxb.backEnd.pojos.User;
import com.zxb.backEnd.pojos.vo.ContactVO;
import com.zxb.backEnd.service.ContactService;
import com.zxb.backEnd.service.RoomService;
import com.zxb.backEnd.service.UserService;
import com.zxb.backEnd.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxb
 * @description 针对表【contact】的数据库操作Service实现
 * @createDate 2025-03-31 17:17:57
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact>
        implements ContactService {

    private final UserService userService;
    private final RoomService roomService;

    /**
     * 根据用户ID查找联系人
     *
     * @return
     */
    @Override
    public List<ContactVO> findByUserId() {
        User user = UserContext.getUser();
        List<Contact> contacts = query().eq("id", user.getId()).list();
        if (contacts == null) return null;
        List<ContactVO> contactVOList = new ArrayList<>();
        for (Contact contact : contacts) {
            User con = userService.getById(contact.getContact());
            Integer roomId = roomService.findByContactId(contact.getContact());
            contactVOList.add(new ContactVO(contact.getContact(), con.getUsername(), roomId));
        }
        return contactVOList;
    }

    /**
     * 查询当前用户的指定联系人
     *
     * @param id
     * @return
     */
    public boolean findContact(Integer id, Integer targetId) {
        return query().eq("id", id).eq("contact", targetId).exists();
    }

    /**
     * 删除联系人
     * @param dto
     * @return
     */
    public boolean removeContacts(List<Integer> ids) {
        return ids.stream().allMatch(id ->
                findContact(UserContext.getUser().getId(), id) &&
                remove(new QueryWrapper<Contact>()
                        .lambda()
                        .eq(Contact::getId, UserContext.getUser().getId())
                        .eq(Contact::getContact, id))
        );
    }
}




