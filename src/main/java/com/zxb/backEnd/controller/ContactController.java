package com.zxb.backEnd.controller;

import com.zxb.backEnd.pojos.vo.ContactVO;
import com.zxb.backEnd.service.ContactService;
import com.zxb.backEnd.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "联系人相关")
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    @Operation(summary = "查询当前用户的联系人")
    public Result<List<ContactVO>> getContacts() {
       List<ContactVO> contactVOList = contactService.findByUserId();
       if (contactVOList == null) return Result.error("查询失败");
       return Result.success(contactVOList);
    }

    @DeleteMapping("/{ids}")
    @Operation(summary = "删除联系人")
    public Result<String> deleteContacts(@PathVariable List<Integer> ids) {
        if (!contactService.removeContacts(ids)) return Result.error("删除失败");
        return Result.success("删除成功");
    }

}
