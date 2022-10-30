package sharm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sharm.pojo.Book;
import sharm.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    // Controller 层调用 Service 层
    @Autowired
    // 这里实现类要和 spring-service.xml 配置的内容对应
    @Qualifier("bookServiceImp")
    private BookService bookService;

    // 1.查询全部的数据，并且返回一个书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model){
        List<Book> books = bookService.queryAllBook();
        model.addAttribute("books", books);
        return "allBook";
    }

    // 2.添加书籍
    // 2.1 跳转到添加书籍页面：web-inf下的文件不能直接访问，因此需要跳转
    @RequestMapping("/toAddBook")
    public String toAddPaper() {
        // 转发，用的还是 /toAddBook 这个 url
        return "addBook";
    }
    // 上面这个 "addBook" 的 url 是 http://localhost:8080/ssmbuild_war_exploded/addBook
    // 下面这个 "addBook" 的 url 是 http://localhost:8080/ssmbuild_war_exploded/book/addBook
    // 2.2 修改页面
    @RequestMapping("/addBook")
    public String addPaper(Book book) {
        bookService.addBook(book);
        return "redirect:allBook";
    }

    // 3.修改书籍
    // 3.1 跳转到修改书籍页面
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Model model, int id) {
        Book book = bookService.queryBookById(id);
        model.addAttribute("book", book);
        return "updateBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Book book) {
        System.out.println(book);
        int i = bookService.updateBook(book);
        if(i == 1){
            System.out.println("修改数据成功");
        }
        return "redirect:allBook";
    }

    // 4.删除数据
    // 4.1 跳转到修改书籍页面
    @RequestMapping("/deleteBook")
    public String deleteBook(int id) {
        int i = bookService.deleteBookById(id);
        if(i == 1){
            System.out.println("删除成功。");
        }
        return "redirect:allBook";
    }

    // 5.根据书籍名检索信息（拓展功能）
    @RequestMapping("/queryBookByName")
    // 如果需要将数据往下传的话，需要来一个 model
    public String queryBookByName(@RequestParam("bookName")String name, Model model) {
        // 由于是新增的功能，且没有使用切面编程，因此需要自底向上进行编写
        List<Book> books = bookService.queryBookByName(name);
        assert books != null;
        if(books.isEmpty()){
            System.out.println("未查到，未查到，未查到");
            model.addAttribute("error", "未查到该书籍");
        }else{
            model.addAttribute("books", books);
        }
        return "allBook";
    }


}
