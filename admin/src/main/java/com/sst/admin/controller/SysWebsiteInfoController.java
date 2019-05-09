package com.sst.admin.controller;


import com.sst.core.constant.CommonConstants;
import com.sst.core.model.ResponseDomain;
import com.sst.service.system.entity.SysUser;
import com.sst.service.system.entity.SysWebsiteInfo;
import com.sst.service.system.service.SysWebsiteInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 站点详情 前端控制器
 * </p>
 *
 * @author xiameng
 * @since 2019-04-10
 */
@Controller
@RequestMapping("/sysWebsiteInfo")
@Slf4j
public class SysWebsiteInfoController {

    @Autowired
    private SysWebsiteInfoService sysWebsiteInfoService;

    @RequestMapping("index.htm")
    public String index(){
        return "system/sysWebsiteInfo";
    }

    @RequestMapping(value = "/getWebsiteInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDomain getWebsiteInfo(){
        SysWebsiteInfo sysWebsiteInfo;
        try{
            sysWebsiteInfo = sysWebsiteInfoService.getWebsiteInfo();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ResponseDomain.getFailedResponse().setResultDesc(e.getMessage());
        }
        return ResponseDomain.getSuccessResponse().setData(sysWebsiteInfo);
    }

    @RequestMapping(value = "/saveWebsiteInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDomain saveWebsiteInfo(SysWebsiteInfo sysWebsiteInfo, HttpServletRequest request){
        try{
            SysUser sysUser = (SysUser)request.getSession().getAttribute(CommonConstants.SESSION_USER_INFO);
            sysWebsiteInfo.setCreateUserId(sysUser.getId());
            sysWebsiteInfo.setCreateTime(new Date());
            sysWebsiteInfo.setDr(0);
            //获取操作人信息
            sysWebsiteInfoService.saveWebsiteInfo(sysWebsiteInfo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ResponseDomain.getFailedResponse().setResultDesc(e.getMessage());
        }
        return ResponseDomain.getSuccessResponse().setResultDesc("提交站点信息成功！");
    }

}

