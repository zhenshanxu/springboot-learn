package com.example.springbootlearn.api.quartz;

import com.example.springbootlearn.entity.assist.ResponseResult;
import com.example.springbootlearn.entity.quartz.QuartzTaskJobBean;
import com.example.springbootlearn.entity.user.UserInfoBean;
import com.example.springbootlearn.service.quartz.QuartzTaskJobService;
import com.example.springbootlearn.utils.enumType.Constant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 15:00:20
 * @Version 1.0
 */

@Api(tags = "定时任务api")
@RestController
@RequestMapping("/quartzTask/api")
public class QuartzTaskJobApi {

    private final QuartzTaskJobService quartzJobService;


    private final static UserInfoBean USER_INFO = new UserInfoBean().setId(1).setUserName("fake");

    public QuartzTaskJobApi(QuartzTaskJobService quartzJobService) {
        this.quartzJobService = quartzJobService;
    }

    /**
     * 添加定时任务
     *
     * @param quartzBean
     * @return
     */
    @PostMapping("/addJob")
    ResponseResult addJob(HttpServletRequest request, @RequestBody QuartzTaskJobBean quartzBean) {
        ResponseResult response = new ResponseResult();
        try {
            quartzBean.setCreator(USER_INFO.getId());
            Map<String, Object> objectMap = quartzJobService.addJob(quartzBean);
            if (objectMap.containsKey(Constant.ERROR_VALUE)) {
                response.setSuccess(Boolean.FALSE).setResult(String.valueOf(objectMap.get(Constant.ERROR_VALUE)));
            } else {
                response.setSuccess(Boolean.TRUE).setResult("添加定时任务完成!");
            }
        } catch (Exception e) {
            response.setSuccess(false).setErrorMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 修改定时任务
     *
     * @param quartzBean
     * @return
     */
    @PostMapping("/updateJob")
    ResponseResult updateJob(@RequestBody QuartzTaskJobBean quartzBean) {
        ResponseResult response = new ResponseResult();
        try {
            Map<String, Object> objectMap = quartzJobService.updateJob(quartzBean);
            if (objectMap.containsKey(Constant.ERROR_VALUE)) {
                response.setSuccess(Boolean.FALSE).setResult(String.valueOf(objectMap.get(Constant.ERROR_VALUE)));
            } else {
                response.setSuccess(Boolean.TRUE).setResult("定时任务修改完成!");
            }
        } catch (Exception e) {
            response.setSuccess(false).setErrorMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 删除任务
     *
     * @param quartzBean
     * @return
     */
    @PostMapping("/deleteJob")
    ResponseResult deleteJob(@RequestBody QuartzTaskJobBean quartzBean) {
        ResponseResult response = new ResponseResult();
        try {
            Map<String, Object> objectMap = quartzJobService.deleteJob(quartzBean);
            if (objectMap.containsKey(Constant.ERROR_VALUE)) {
                response.setSuccess(Boolean.FALSE).setResult(String.valueOf(objectMap.get(Constant.ERROR_VALUE)));
            } else {
                response.setSuccess(Boolean.TRUE).setResult("定时任务删除完成!");
            }
        } catch (Exception e) {
            response.setSuccess(false).setErrorMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 查询任务
     *
     * @param quartzBean
     * @return
     */
    @PostMapping("/queryJob")
    ResponseResult queryJob(@RequestBody QuartzTaskJobBean quartzBean) {
        ResponseResult response = new ResponseResult();
        try {
            List<QuartzTaskJobBean> quartzTaskJobList = quartzJobService.queryJob(quartzBean);
            response.setSuccess(Boolean.TRUE).setResult(quartzTaskJobList);
        } catch (Exception e) {
            response.setSuccess(false).setErrorMessage(e.getMessage());
        }
        return response;
    }

}
