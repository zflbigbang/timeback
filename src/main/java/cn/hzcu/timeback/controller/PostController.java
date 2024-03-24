package cn.hzcu.timeback.controller;


import cn.hzcu.timeback.entity.Post;
import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.entity.User;
import cn.hzcu.timeback.service.IPostService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/post")
@Api
public class PostController {
    @Autowired
    private IPostService postService;
    @GetMapping("/list")
    @ApiOperation(value = "list")
    public R<List<Post>> list(){
        List<Post> list = postService.list();
        return R.success(list);
    }
    @GetMapping()
    @ApiOperation(value = "getById")
    public R<Post> updateManager(@RequestParam Integer id){
        return R.success(postService.getById(id));
    }

    @GetMapping("/userid")
    @ApiOperation(value = "getByUserId")
    public R<List<Post>> getByUserId(@RequestParam Integer userId) {
        List<Post> postList = postService.lambdaQuery().eq(Post::getUserId,userId).list();
        return R.success(postList);
    }

    @PutMapping()
    @ApiOperation(value = "update")
    public R<String> updateManager(@RequestBody @Validated(Post.Update.class) Post post){
        postService.updateById(post);
        return R.success();
    }
    @PostMapping()
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody @Validated(Post.Add.class) Post post){
        postService.save(post);
        return R.success();
    }
    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> save(@RequestParam Integer id){
        postService.removeById(id);
        return R.success();
    }

}
