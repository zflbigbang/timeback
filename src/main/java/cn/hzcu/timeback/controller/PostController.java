package cn.hzcu.timeback.controller;


import cn.hzcu.timeback.entity.Post;
import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.service.IPostService;
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
public class PostController {
    @Autowired
    private IPostService postService;
    @GetMapping("/list")
    public R<List<Post>> list(){
        List<Post> list = postService.list();
        return R.success(list);
    }
    @PutMapping("/update")
    public R<String> updateManager(@RequestBody Post post){
        postService.updateById(post);
        return R.success("更新成功");
    }
    @PostMapping("/save")
    public R<String> save(@RequestBody Post post){
        post.setId(postService.list().size()+1);
        postService.save(post);
        return R.success("添加成功");
    }


}
