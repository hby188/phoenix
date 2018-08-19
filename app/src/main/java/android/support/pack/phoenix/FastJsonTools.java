package android.support.pack.phoenix;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import net.sf.json.JSONObject;

/**
 * @author yinhb
 * @date 20180817
 */
public class FastJsonTools {
	/**
	 *
	 * @param object
	 * @return
	 */
	public static String createJsonString(Object object) {
		String jsonString = JSON.toJSONString(object);
		return jsonString;
	}
	/**
	 * 完成单个javabean的解析
	 * @param jsonString
	 * @param cls
	 * @return
	 */
	public static <T> T getInstance(String jsonString,Class<T> cls){
		T t = null;
		try {
			t = JSON.parseObject(jsonString,cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	/**
	 * 多个对象list的解析
	 * @param jsonString
	 * @param cls
	 * @return
	 */
	public static <T> List<T> getPersons(String jsonString,Class<T> cls){
		List<T> list = new ArrayList<T>();
		try {
			list = JSON.parseArray(jsonString,cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * list<map>对象的解析
	 * @param jsonString
	 * @return
	 */
	public static List<Map<String,Object>> listKeyMap(String jsonString){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = JSON.parseObject(jsonString,new TypeReference<List<Map<String,Object>>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static <T> List<T> getCasesList(String jsonString,Class<T> cls){
		List<T> list = new ArrayList<T>();
		try {
			list = JSON.parseArray(jsonString,cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		String msg = "";
//		JsonService jsonService = new JsonService();

		//解析单个对象生成json报文 {"address":"beijing","id":1001,"name":"jack"}
//		Person person = jsonService.getPerson();
//		msg = JsonTools.createJsonString("person", person); //"person"的key可以不要。但采用标准 key/value形式


//		msg = FastJsonTools.createJsonString(person); //阿里巴巴的工具类产生json字符串
//		System.out.println(msg);
//		System.out.println("@@@解析："+FastJsonTools.getPerson(msg,Person.class));


		/*//多个对象的解析 List<T> [{"address":"beijing","id":1001,"name":"jack"},{"address":"南京","id":1002,"name":"rose"}]
		msg = FastJsonTools.createJsonString(jsonService.getPersons());
		System.out.println(msg);
		System.out.println("解析："+FastJsonTools.getPersons(msg,Person.class));
		//list<map>对象的解析 [{"address":"上海","id":1002,"name":"rose"},{"address":"beijing","id":1001,"name":"jack"}]
		msg = FastJsonTools.createJsonString(jsonService.getListMaps());
		System.out.println(msg);
		System.out.println("解析："+FastJsonTools.listKeyMap(msg));
		//解析生成列表["beijing","上海","湖南"]
		msg = FastJsonTools.createJsonString(jsonService.getListString());
		System.out.println(msg);
		System.out.println("解析："+FastJsonTools.getPersons(msg,String.class));

		//测试报文：
		msg = "[{\"menuId\":\"11\",\"menuName\":\"首页\", \"menuUrl\":\"http://oss.cmgame.com/egbi/appPageHome.do\"},{\"menuId\":\"12\",\"menuName\":\"产品\", \"menuUrl\":\"http://oss.cmgame.com/egbi/appProduct.do\"},{\"menuId\":\"13\",\"menuName\":\"渠道\", \"menuUrl\":\"http://oss.cmgame.com/egbi/appChannel.do\"},{\"menuId\":\"14\",\"menuName\":\"排名\", \"menuUrl\":\"http://oss.cmgame.com/egbi/appRanking.do\"}]";
		System.out.println("测试报文："+FastJsonTools.getPersons(msg, Menu.class));
		List<Menu> menuList = FastJsonTools.getPersons(msg, Menu.class);
		for(Menu menu:menuList)
			menu.setMenuUrl(new StringBuffer(menu.getMenuUrl()).append("?token=")+"a1dadbca3dac");
		System.out.println("测试生成json报文："+FastJsonTools.createJsonString(menuList));
		VersionInfo version = new VersionInfo("0","测试更新","http://112.4.17.164:80/MTPPResources/test/123.apk","1.0.1");
		System.out.println(FastJsonTools.createJsonString(version));


		//测试混合对象报文
		MixedObject mixedObject = new MixedObject();
		mixedObject.setMenus(menuList);
		mixedObject.setPerson(person);
		System.out.println("复杂对象："+FastJsonTools.createJsonString(mixedObject));
		String mixedString = FastJsonTools.createJsonString(mixedObject);
		List<Menu> menus = FastJsonTools.getPerson(mixedString, MixedObject.class).getMenus();
		for(Menu menu:menus)
		System.out.println("复杂对象解析："+menu.toString());
		*/

//		String mixedString2 = "[{\"menuId\":\"11\",\"menuName\":\"首页\", \"menuUrl\":\"http://oss.cmgame.com/egbi/appPageHome.do\"},{\"menuId\":\"12\",\"menuName\":\"产品\", \"menuUrl\":\"http://oss.cmgame.com/egbi/appProduct.do\"},{\"menuId\":\"13\",\"menuName\":\"渠道\", \"menuUrl\":\"http://oss.cmgame.com/egbi/appChannel.do\"},{\"menuId\":\"14\",\"menuName\":\"排名\", \"menuUrl\":\"http://oss.cmgame.com/egbi/appRanking.do\"}]";;
//		List<Menu> menus2 = FastJsonTools.getCasesList(mixedString2, Menu.class);
//		for(Menu menu:menus2)
//			System.out.println("复杂对象解析...："+menu.toString());
//
//		String verifyCode = "123456";
//		List<String> verifyCodeList = new ArrayList<String>();
//		verifyCodeList.add(verifyCode);
//		Map codeMap = new HashMap<String ,String>();
//		codeMap.put("verifyCode", verifyCode);
//		System.out.println("验证码："+FastJsonTools.createJsonString(codeMap));
	}
}
