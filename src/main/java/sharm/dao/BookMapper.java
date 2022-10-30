package sharm.dao;

import org.apache.ibatis.annotations.Param;
import sharm.pojo.Book;

import java.util.List;

/**
 * 接口约束的是方法
 */
public interface BookMapper {

    // 增加一本书
    public int addBook(Book book);

    // 根据 id 删除一本书
    // 好奇怪，明明对应的 sql 语句是不返回内容的，为什么还可以接收到 int 型的 1，难怪 SQL 语句执行成功会返回 1
    public int deleteBookById(int id);

    // 更新书
    public int updateBook(Book book);

    // 根据 id，返回一本书
    public Book queryBookById(int id);

    // 查询全部的书
    List<Book> queryAllBook();

    // 根据书籍名进行检索
    // 可能存在同名书籍，所以这里使用 List 进行接收
    // 可以在接口上加上 @Param 注解，这样出错的概率会小很多
    List<Book> queryBookByName(@Param("bookName") String name);
}
