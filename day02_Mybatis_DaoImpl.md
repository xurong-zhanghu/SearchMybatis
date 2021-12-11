# day02_Mybatis_DaoImpl

## 1.探究findAll()方法调用过程

第一步：

图示：1.sqlSession.selectList<img src="D:\Java\Mybatis\source\SearchMybatis\image\image-20211211183409723.png" alt="image-20211211183409723" style="zoom:150%;" />

点击上图中selectList可知，其为SqlSession接口中的一个抽象方法

如图<img src="D:\Java\Mybatis\source\SearchMybatis\image\image-20211211183833419.png" alt="image-20211211183833419" style="zoom: 67%;" />

第二步(2)：

既然是DefaultSqlSession类实现了该接口，自然要实现SqlSession接口中的方法，很自然的想法就是去看一看DefaultSqlSession类中的方法

![image-20211211184927268](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211184927268.png)

进入到DefaultSqlSession内部去看看

找到selectList方法：<img src="D:\Java\Mybatis\source\SearchMybatis\image\image-20211211185344717.png" alt="image-20211211185344717" style="zoom:60%;" />

第三步：探究第3个selectList()

点击query进去如图：<img src="D:\Java\Mybatis\source\SearchMybatis\image\image-20211211190303684.png" alt="image-20211211190303684" style="zoom:67%;" />

接下来就是探究又是哪一个类实现了该接口中的方法，采用打断点调试

如图![image-20211211190744401](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211190744401.png)



第四步：自然而然想到进入到CachingExecutor类中去查看query()方法

如图：![image-20211211191009548](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211191009548.png)

进入方法如图：

![image-20211211192417367](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211192417367.png)



第五步：自然想到是到SimpleExecutor中去看一下该query()方法的实现

如图所示<img src="D:\Java\Mybatis\source\SearchMybatis\image\image-20211211192908789.png" alt="image-20211211192908789" style="zoom:60%;" />



第六步：自然想到到BaseExecutor中寻找query()方法的实现

如图：BaseExecutor中的query()方法<img src="D:\Java\Mybatis\source\SearchMybatis\image\image-20211211193632655.png" alt="image-20211211193632655" style="zoom:55%;" />

点进DqueryFromDatabase去看看：

如图：![image-20211211193946078](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211193946078.png)

点进doquery()方法去看看：

如图：![image-20211211194129402](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211194129402.png)

到这里，可知道BaseExecutor类中的doQuery()被子类SimpleExecutor实现，所以query实际执行是SimpleExecutor中的doQuery方法。



第七步：自然想到到去查看SimpleExecutor中的doQuery方法

SimpleExecutor中的doQuery方法：

如图：![image-20211211195544624](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211195544624.png)

StatementHandler接口:![image-20211211195749441](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211195749441.png)

打断点调试看是哪一个类实现了上述接口：

如图：RoutingStatementHandler实现了Statement接口中的query()方法。![image-20211211200310861](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211200310861.png)

第八步：很自然找到RoutingStatementHandler去看看query()的实现方法

如图：RoutingStatementHandler实现了接口Statementhandler

![image-20211211201458509](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211201458509.png)

如图：StatementHandler接口中query方法

![image-20211211201704288](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211201704288.png)

如图：RoutingStatementHandler类中的query()方法

![image-20211211202034219](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211202034219.png)

第九步：到PreparedStatementHandler类中去看看

<img src="D:\Java\Mybatis\source\SearchMybatis\image\image-20211211202247457.png" alt="image-20211211202247457" style="zoom:67%;" />

<img src="D:\Java\Mybatis\source\SearchMybatis\image\image-20211211202904407.png" alt="image-20211211202904407" style="zoom:150%;" />

在该query方法中使用了execute()执行了查询方法，底下return是对查询方法结果集封装的返回。

![image-20211211204733123](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211204733123.png)

第十步：对返回结果集进行探索

![image-20211211203607190](D:\Java\Mybatis\source\SearchMybatis\image\image-20211211203607190.png)



点进hanleResultSets()方法，如图：

<img src="D:\Java\Mybatis\source\SearchMybatis\image\image-20211211203806175.png" alt="image-20211211203806175" style="zoom:67%;" />

<img src="D:\Java\Mybatis\source\SearchMybatis\image\image-20211211203514890.png" alt="image-20211211203514890" style="zoom:50%;" />

查看DefaultResultSetHandler:

<img src="D:\Java\Mybatis\source\SearchMybatis\image\image-20211211205032221.png" alt="image-20211211205032221" style="zoom:67%;" />

分析到此结束：后面步骤涉及结果集的分装