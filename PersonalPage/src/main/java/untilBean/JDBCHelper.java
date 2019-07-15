package untilBean;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
public class JDBCHelper {

    private static String driver ;
    private static String url ;
    private static String username ;
    private static  String password ;
    // 用来表示一个连接
    private static Connection conn ;

    static{
        config() ;
    }

    private static void  config(){

            driver = "org.h2.Driver" ;
            url = "jdbc:h2:D:\\temp\\db\\mydb" ;
            username = "sa" ;
            password = "" ;


    }
   // 加载驱动
    private static void load(){
        try {

            Class.forName(driver) ;
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败" + e.getMessage());
        }
    }

    /**
     * 判断连接是否有效
     * @return false 表示连接 无效
     *         true 表示连接有效
     */
    private static boolean isValid(){
        try {
            if( conn != null ){
                // 如果 该链接在 3 秒内 是 有效的 ， 那么返回 true
               return  conn.isValid( 3 ) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false ;
    }
    // 建立连接
    private static Connection conn(){
        // 加载驱动
        load() ;
        try {
            if(  ! isValid() ){ // 证明 此时的连接 是已经关闭的 或者是 无效的 ，所以应该创建这个连接
                conn = DriverManager.getConnection( url , username , password ) ;
            }
        } catch (SQLException e) {
            System.out.println("建立连接失败 " + e.getMessage() );
        }
        return conn  ;
    }
    // 创建Statement 对象
    private static Statement state(){
        conn = conn() ;
        Statement st = null ;
        try {
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE) ;
        } catch (SQLException e) {
            System.out.println("创建 Statement 对象失败 " + e.getMessage() );
        }
        return st ;
    }

    // 创建 PreparedStatment 对象
    private static PreparedStatement prepare( String SQL , boolean autoGeneartedKeys){
        conn = conn() ;
        PreparedStatement ps = null ;
        try {
            if( autoGeneartedKeys ) {
                ps = conn.prepareStatement(SQL , Statement.RETURN_GENERATED_KEYS );
            }else{

                ps = conn.prepareStatement( SQL ,ResultSet.TYPE_SCROLL_SENSITIVE) ;
            }
        } catch (SQLException e) {
            System.out.println("创建 PreparedStatement 对象失败 " + e.getMessage() );
        }
        return ps ;
    }
    /**
     * 执行 DDL、和 DML
     */
    public static boolean execute( String SQL , Object... params ){
        boolean flag = false ;
        if( SQL == null || SQL.trim().isEmpty() || SQL.trim().toLowerCase().startsWith("select") ){
            throw new RuntimeException("你传入的SQL为空或是一个查询语句") ;
        }
        Connection c = null  ;
        if( params.length > 0 ){
            // 此时的SQL 语句中有占位符，并且 传入了参数
            PreparedStatement ps = prepare( SQL ,  false  ) ;
            try {
                c = ps.getConnection() ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                // 填充占位符
                for( int i = 0 ; i < params.length ; i ++){
                    ps.setObject( i+1 , params[i] ) ;
                }
                ps.executeUpdate() ;

                flag = true ;
            } catch (SQLException e) {
                System.out.println("操作失败 " + e.getMessage() );

            }
        }else{
            Statement st = state() ;
            try {
                c = st.getConnection() ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                st.executeUpdate( SQL ) ;

                flag = true ;
            } catch (SQLException e) {
                System.out.println("执行失败" + e.getMessage() );

            }
        }
        return flag ;
    }

    /**
     * 执行 DQL 语句
     */
    public static ResultSet query( String SQL , Object ... params  ){
        ResultSet rs = null ;
        if( SQL == null || SQL.trim().isEmpty() || !SQL.trim().toLowerCase().startsWith("select") ){
            throw new RuntimeException("你传入的SQL为空或不是一个查询语句") ;
        }
        if( params.length > 0 ){
            PreparedStatement ps = prepare( SQL  , false ) ;
            try {
                for( int i  =0 ; i < params.length ; i ++ ){
                    ps.setObject( i + 1 , params[i] ) ;
                }
                rs = ps.executeQuery() ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            Statement st = state() ;
            try {
                rs = st.executeQuery( SQL ) ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rs ;
    }

    /**
     * 分页查询
     * @param
     */
    public static CachedRowSet queryByPage(String sql , int pageSize
            , int page)
    {
            ResultSet rs=query(sql);

            // 使用RowSetProvider创建RowSetFactory

        try {
            RowSetFactory factory = RowSetProvider.newFactory();
            // 创建默认的CachedRowSet实例
            CachedRowSet cachedRs = factory.createCachedRowSet();

            // 设置每页显示pageSize条记录
            cachedRs.setPageSize(pageSize);
            // 使用ResultSet装填RowSet，设置从第几条记录开始
            cachedRs.populate(rs , (page - 1) * pageSize + 1);
            return cachedRs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    // 关闭资源
    public static void release( Object o ){
        if( o != null ){
            if( o instanceof  Connection ){
                Connection c = (Connection) o ;
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if( o instanceof  Statement ){
                Statement st = (Statement) o ;
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if( o instanceof  ResultSet ){
                ResultSet rs = (ResultSet) o ;
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
