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

package com.asialjim.clinc.service.prescription;

import com.asialjim.clinc.api.PrescriptionRecordApi;
import com.asialjim.clinc.cons.ClincRoleCode;
import com.asialjim.microapplet.common.security.MamsSession;
import com.asialjim.microapplet.common.security.MamsSessionAttribute;
import com.asialjim.microapplet.mams.app.cons.ChannelAppType;
import com.asialjim.microapplet.mams.app.cons.ChannelType;
import com.asialjim.microapplet.mams.user.api.ChlUserApi;
import com.asialjim.microapplet.mams.user.api.IdCardUserApi;
import com.asialjim.microapplet.mams.user.api.UserApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 专业用户用药提醒
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/22, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Component
public class ProfessionalUserPrescriptionReminder extends BasePrescriptionReminder {
    private final IdCardUserApi idCardUserApi;
    private final ChlUserApi chlUserApi;

    public ProfessionalUserPrescriptionReminder(
            PrescriptionRecordApi prescriptionRecordApi,
            IdCardUserApi idCardUserApi,
            ChlUserApi chlUserApi,
            UserApi userApi,
            MamsSessionAttribute mamsSessionAttribute) {

        super(mamsSessionAttribute, prescriptionRecordApi, userApi);
        this.idCardUserApi = idCardUserApi;
        this.chlUserApi = chlUserApi;
    }

    @Override
    public boolean support(long role) {
        return RoleCode.contains(role, ClincRoleCode.DOCTOR_BIT)
                || RoleCode.contains(role, ClincRoleCode.NURSE_BIT);
    }

    @Override
    public long roleBit() {
        // 专业用户具有更高优先级
        return Long.MAX_VALUE;
    }

    @Override
    protected void beforeQuery(String name, String idNo, String phone,
                               List<String> useridList,
                               List<String> doctorList,
                               List<String> nurseList) {

        MamsSession mamsSession = mamsSessionAttribute.currentLoginSession();
        String appid = mamsSession.getAppid();

        if (!StringUtils.isAllBlank(name, idNo)) {
            List<String> list = this.idCardUserApi.queryUseridByNameOfIdNoForAppid(name, idNo, appid);
            useridList.addAll(list);
        }

        if (StringUtils.isNotBlank(phone)) {
            String chl = mamsSession.getChl();
            String chlAppid = mamsSession.getChlAppid();
            String chlAppType = StringUtils.equals(chl, ChannelType.WeChat.getCode()) ? ChannelAppType.WeChatPhone.getCode() : ChannelAppType.Phone.getCode();
            List<String> list = chlUserApi.queryUseridByChlAppidTypeForAppid(chl, chlAppid, chlAppType, appid);
            useridList.addAll(list);
        }
    }
}