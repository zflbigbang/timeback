package cn.hzcu.timeback.controller;


import cn.hzcu.timeback.entity.Post;
import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.service.IPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @PutMapping("/update")
    @ApiOperation(value = "update")
    public R<String> updateManager(@RequestBody Post post){
        postService.updateById(post);
        return R.success("更新成功");
    }
    @PostMapping("/save")
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody Post post){
        post.setId(postService.list().size()+1);
        postService.save(post);
        return R.success("添加成功");
    }


}
