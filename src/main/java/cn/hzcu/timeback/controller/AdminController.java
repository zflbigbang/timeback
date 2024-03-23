package cn.hzcu.timeback.controller;


import cn.hzcu.timeback.entity.Admin;
import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.service.IAdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private IAdminService AdminService;
    @PostMapping("/login")
    public R<Admin> login(@RequestBody Admin admin){
        String password = admin.getPassword();
        //从数据库中查找用户
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getEmail,admin.getEmail());
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
    public R<List<Admin>> list(){
        List<Admin> list = AdminService.list();
        return R.success(list);
    }
    @PutMapping("/update")
    public R<String> updateAdmin(@RequestBody Admin Admin){
        AdminService.updateById(Admin);
        return R.success("更新成功");
    }

}
