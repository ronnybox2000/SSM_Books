import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sharm.pojo.Book;
import sharm.service.BookService;

public class MyTest {
    @Test
    public void test1(){
        // 对配置文件建立一个对象
        // 查询书籍
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImp = (BookService) context.getBean("bookServiceImp");
        for (Book books : bookServiceImp.queryAllBook()) {
            System.out.println(books);
        }
    }

    @Test
    public void test2(){
        // 增加书籍
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImp = (BookService) context.getBean("bookServiceImp");



    }
}
