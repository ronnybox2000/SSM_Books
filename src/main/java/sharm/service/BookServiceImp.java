package sharm.service;

import sharm.dao.BookMapper;
import sharm.pojo.Book;

import java.util.List;

public class BookServiceImp implements BookService {
    //1. 一般来说 Dao 与 Service 的内容是相似的，只不过业务层可以在 Dao 层上增加一些其它的操作，类似于开闭原则；
    //2. 所以说是只将 Dao 层修改为 xml 形式，service 层还是接口加实现类的形式。

    // 调用 dao 层的操作，设置一个 set 接口，方便 Spring 管理
    private BookMapper bookMapper;
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int deleteBookById(int id) {
        return bookMapper.deleteBookById(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public Book queryBookById(int id) {
        return bookMapper.queryBookById(id);
    }

    @Override
    public List<Book> queryAllBook() {
        return bookMapper.queryAllBook();
    }

    @Override
    public List<Book> queryBookByName(String name) {
        return bookMapper.queryBookByName(name);
    }
}
