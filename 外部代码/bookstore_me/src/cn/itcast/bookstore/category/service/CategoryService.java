package cn.itcast.bookstore.category.service;

import java.util.List;

import cn.itcast.bookstore.category.dao.CategoryDao;
import cn.itcast.bookstore.category.domain.Category;

public class CategoryService {
  private static final String String = null;
private CategoryDao categoryDao=new CategoryDao();
  public List<Category> findAll(){
	  return categoryDao.findAll();
  }
  public void add(String cname) throws CategoryException{
	  Category category=categoryDao.findByCname(cname);
	  if(category!=null) throw new CategoryException("该类别已经存在");
	  category=new Category();
	  category.setCname(cname);
	  category.setCid(categoryDao.getTotal()+1+"");
	  categoryDao.add(category);
  }
  public void delete(String cid) throws CategoryException{
	 if(categoryDao.findBooksByCid(cid)>0) throw new CategoryException("该类别下还有图书，不能删除");
	 categoryDao.delete(cid);
  }
public Category findByCid(String cid) throws CategoryException {
    Category category=categoryDao.findByCid(cid);
    if(category==null) throw new CategoryException("不存在此类别");
    return category;
}
public  void edit(Category category) {
	categoryDao.edit(category.getCname(),category.getCid());
}
}
