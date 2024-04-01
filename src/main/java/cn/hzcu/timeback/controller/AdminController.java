package cn.hzcu.timeback.controller;


import cn.hzcu.timeback.entity.Admin;
import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.service.IAdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-22
 */
@RestController
@RequestMapping("/admin")
@Api
@CrossOrigin
public class AdminController {
    @Autowired
    private IAdminService AdminService;
    @PostMapping("/login")
    @ApiOperation(value = "login")
    public R<Admin> login(@RequestBody Admin admin){
        String password = admin.getPassword();
        //从数据库中查找用户
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getName,admin.getName());
        Admin man = AdminService.getOne(queryWrapper);
        //如果没有找到
        if(man == null){
            return R.error("登录失败");
        }
        //找到后匹配密码
        if(!man.getPassword().equals(password)){
            return R.error("密码错误");
        }
        return  R.success(man);
    }
    @GetMapping("/list")
    @ApiOperation(value = "list")
    public R<IPage> list(@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size){
        Page page = new Page(current,size);
        IPage adminPage = AdminService.page(page);
        return R.success(adminPage);
    }
    @GetMapping()
    @ApiOperation(value = "getById")
    public R<Admin> getAdmin(@RequestParam Integer id){
        return R.success(AdminService.getById(id));
    }
    @PostMapping
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody @Validated(Admin.Add.class) Admin Admin){
        AdminService.save(Admin);
        return R.success();
    }
    @PutMapping()
    @ApiOperation(value = "update")
    public R<String> updateAdmin(@RequestBody @Validated(Admin.Update.class) Admin Admin){
        AdminService.updateById(Admin);
        return R.success();
    }

    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> deleteAdmin(@RequestParam Integer id){
        AdminService.removeById(id);
        return R.success();
    }

}
