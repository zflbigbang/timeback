package cn.hzcu.timeback.controller;


import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.entity.User;
import cn.hzcu.timeback.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-22
 */
@RestController
@RequestMapping("/user")
@Api
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "login")
    @PostMapping("/login")

    public R<User> login(@RequestBody User user){
        return R.error("hh");
    }



}
