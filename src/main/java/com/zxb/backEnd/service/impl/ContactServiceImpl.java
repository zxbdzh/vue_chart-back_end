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
import java.util.Objects;

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
     *
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
                && roomService.removeBatchByIds(List.of(roomService.findByContactId(id)))
        );
    }

    /**
     * 添加联系人
     *
     * @param id
     * @return
     */
    public boolean addContact(Integer id) {
        if (!userService.existUser(id)) return false;
        if (Objects.equals(id, UserContext.getUser().getId())) return false;
        if (findContact(UserContext.getUser().getId(), id)) return false;
        Contact contact = new Contact();
        contact.setId(UserContext.getUser().getId());
        contact.setContact(id);
        return save(contact);
    }

    @Override
    public boolean addContact(String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) return false;
        if (Objects.equals(user.getId(), UserContext.getUser().getId())) return false;
        if (roomService.findByContactId(user.getId()) == null && findContact(user.getId(), UserContext.getUser().getId()))
            roomService.createRoom(user.getId());
        return addContact(user.getId());
    }
}




