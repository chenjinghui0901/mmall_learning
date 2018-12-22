package com.mmall.controller.backend;

import com.mmall.common.ServerResponse;
import com.mmall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by geely
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(String categoryName,
              @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
//        }
//        //校验一下是否是管理员
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            //是管理员
//            //增加我们处理分类的逻辑
//            return iCategoryService.addCategory(categoryName, parentId);
//
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
//        }

        //全部通过拦截器验证是否登录以及权限
        return iCategoryService.addCategory(categoryName,parentId);
    }

    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(Integer categoryId, String categoryName) {

        //全部通过拦截器验证是否登录以及权限
        return iCategoryService.updateCategoryName(categoryId,categoryName);
    }

    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(
            @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {

        //全部通过拦截器验证是否登录以及权限
        return iCategoryService.getChildrenParallelCategory(categoryId);
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(
            @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {

        //全部通过拦截器验证是否登录以及权限
        return iCategoryService.selectCategoryAndChildrenById(categoryId);
    }


}
