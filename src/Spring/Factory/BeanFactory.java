package Spring.Factory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class BeanFactory {

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> clazz) {
        T t = null;

        try {
            t = (T) clazz.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;

    }

    /**
     * 解析Bean.properties
     */

    private static Map<String, String> map = new HashMap<>();
    public static Map<String, Object> ioc = new HashMap<>();

    static {
        //得到bean.properties
        InputStream inputStream = BeanFactory.class.getResourceAsStream("/Spring/Bean.properties");
        Properties properties = new Properties();
        //流加载进properties
        try {
            properties.load(inputStream);
            Set<Map.Entry<Object, Object>> entries = properties.entrySet();
            for (Map.Entry<Object, Object> enter : entries) {
                map.put(enter.getKey().toString(), enter.getValue().toString());

                //ioc容器
                Class<?> forname = Class.forName(enter.getValue().toString());
                Object obj = forname.newInstance();
                ioc.put(enter.getKey().toString(), obj);
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解析配置文件异常");
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String key) {
        T t = null;

        try {
            Class<?> clazz = Class.forName(map.get(key));
            t = (T) clazz.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;

    }

}
