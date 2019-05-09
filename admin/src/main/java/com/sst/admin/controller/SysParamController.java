package com.sst.admin.controller;


import com.sst.core.model.ResponseDomain;
import com.sst.service.system.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 系统参数表 前端控制器
 * </p>
 *
 * @author XuHaidong
 * @since 2019-04-08
 */
@Controller
@RequestMapping("/sysParam")
public class SysParamController {

    @Autowired
    private SysParamService sysParamService;

    @RequestMapping("/getValueByCode")
    @ResponseBody
    public ResponseDomain getValueByCode(String code) {
        String value = sysParamService.queryValueByCode(code);
        return ResponseDomain.getSuccessResponse().setData(value);
    }

}

