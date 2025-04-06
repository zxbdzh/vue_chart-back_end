package com.zxb.backEnd.service;

import com.zxb.backEnd.pojos.Contact;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxb.backEnd.pojos.vo.ContactVO;

import java.util.List;

/**
* @author zxb
* @description 针对表【contact】的数据库操作Service
* @createDate 2025-03-31 17:17:57
*/
public interface ContactService extends IService<Contact> {

    /**
     * 根据用户id查询联系人
     * @return
     */
    List<ContactVO> findByUserId();

    /**
     * 查询当前用户的指定联系人
     * @param id
     * @return
     */
    boolean findContact(Integer id, Integer targetId);

    /**
     * 删除联系人
     * @param dto
     * @return
     */
    boolean removeContacts(List<Integer> ids);
}
