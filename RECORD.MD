一、目录结构</br>
 ├ Anchor	                       总目录</br>
 │ └ src.main	               目前框架主目录</br>
 │   ├java                     基础框架JAVA代码目录</br>
 │   │ ├cn.sheetanchor
 │   │ │ ├common                  公共模块目录</br>
 │   │ │ │ ├ beanvalidator       实体类bean验证相关实体类</br>
 │   │ │ │ ├ config              日志相关工具类</br>
 │   │ │ │ ├ hibernate           hibernate相关工具类</br>
 │   │ │ │ ├ interceptor         拦截器相关工具类</br>
 │   │ │ │ ├ listener            监听相关工具类</br>
 │   │ │ │ ├ mapper              映射转换工具类</br>
 │   │ │ │ ├ page                分页相关工具类</br>
 │   │ │ │ ├ persistence         持久层工具类</br>
 │   │ │ │ ├ security            安全相关工具类</br>
 │   │ │ │ ├ service             服务相关工具类</br>
 │   │ │ │ ├ servlet             servlet相关工具类</br>
 │   │ │ │ ├ utils               各种小工具类</br>
 │   │ │ │ └ web                 模型控制器层相关工具类</br>
 │   │ │ └sparrow                 业务功能模块目录</br>
 │   │ │   └ sys                  系统基础模块</br>
 │   │ └ org                      mybaties扩展工具类</br>
 │   ├resources                    资源目录</br>
 │   │  ├cache                    缓存相关配置文件</br>
 │   │  ├log4j.properties         日志配置文件</br>
 │   │  ├sparrow.properties       系统基础配置文件</br>
 │   │  ├spring-context.xml       数据库相关配置文件</br>
 │   │  ├spring-context-shiro.xml Shiro配置文件</br>
 │   │  └spring-mvc.xml           Spring基础配置文件</br>
 │   └webapp                    页面资源目录</br>
 │       ├static                静态资源/第三方插件目录</br>
 │       └WEB-INF</br>
 │          ├tags               tag标签目录（tag文件作用一般是一段小代码，类似include文件的作用）</br>
 │          ├tlds               jstl标签目录（tld文件作用比较复杂，可以实现很多高级的作用）</br>
 │          ├views              页面资源</br>
 │          ├ckfinder.xml       CK编辑器配置文件</br>
 │          └web.xml</br>
 │</br>
 │</br>
 │</br>
------------------------------------------------------------------------------------------------------------------------
 二、开发规范</br>
 1、类注释：必须包含类功能描述，作者，时间</br>
 2、方法注释：除非常容易理解的方法如get、form、save、delete等方法外的方法必须加注释来说明用途，传递参数。</br>
 3、按照以上命名结构进行文件分配存放。</br>
 4、视图文件名要加模块或功能前缀，例如actTaskTodoList.jsp，代表：工作流任务待办列表,不要写成list.jsp或todoList.jsp或taskTodoList.jsp。