/*
 *    Copyright 2014-2025 <a href="mailto:asialjim@qq.com">Asial Jim</a>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.asialjim.clinc.web;

import com.asialjim.clinc.cons.ClincRoleCode;
import com.asialjim.clinc.service.PrescriptionRemindService;
import com.asialjim.clinc.vo.PrescriptionRemindVo;
import com.asialjim.microapplet.common.page.PageData;
import com.asialjim.microapplet.commons.security.RoleCode;
import com.asialjim.microapplet.commons.security.RoleNeed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户提醒器
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/22, &nbsp;&nbsp; <em>version:1.0</em>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/prescription/reminder")
public class PrescriptionRemindController {
    private final PrescriptionRemindService prescriptionRemindService;

    @GetMapping("/list")
    @RoleNeed(any = {RoleCode.PHONE_BIT, ClincRoleCode.NURSE_BIT, ClincRoleCode.DOCTOR_BIT})
    public PageData<PrescriptionRemindVo> list(
            @RequestParam(required = false, defaultValue = "1") Long page,
            @RequestParam(required = false, defaultValue = "5") Long size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String idNo,
            @RequestParam(required = false) String phone) {

        return this.prescriptionRemindService.query(page, size, name, idNo, phone);
    }
}