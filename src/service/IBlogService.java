
package service;

import java.util.List;

public interface IBlogService<B> {
    
    void addBlog(B b);
    void deleteBlog(int id);
    void updateBlog(B b);
    List<B>readAllBlogs();
    B readById(int id);
}
