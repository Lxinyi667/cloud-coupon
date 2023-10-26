package top.lxyi.content.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.lxyi.content.model.Book;

@RestController
public class ContentController {
    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable int id){
        return Book.builder().id(id).name("微服务应用技术").author("后端开发").isbn("996996996").build();
    }
}
