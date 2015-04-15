package helper;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class HtmlHelper {
	public static final String ENCORDING = "UTF-8";

	public static void firstHttp(PrintWriter out,String title,String css){
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"ja\">");
		out.println("  <head>");
		out.println("    <meta charset=\"" + ENCORDING + "\" />");
		out.println("    <title>" + title + "</title>");
		insertCss(out,"./css/common.css");
		if(css != null && !css.equals("")) insertCss(out,css);
		out.println("  </head>");
		out.println("  <body>");
		out.println("    <section>");
	}

	//css挿入
	public static void insertCss(PrintWriter out,String cssPath){
		out.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"" + cssPath + "\" />");
	}

	public static void endHttp(PrintWriter out){
		out.println("    </section>");
		out.println("  </body>");
		out.println("</html>");
	}

	//パラメータ探索
	public static boolean searchParameter(String checkParamName,HttpServletRequest request){
		boolean result = false;
		Enumeration<String> names = request.getParameterNames();
		String name = "";
		while(names.hasMoreElements()){
			name = (String)names.nextElement();
			if(name.equals(checkParamName)){
				result = true;
				break;
			}
		}
		return result;
	}

	//Tomcat文字化け用
	public static String encording(String targetStr){
		String encordStr = null;
		try {
			encordStr = new String(targetStr.getBytes("ISO8859-1"),ENCORDING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encordStr;
	}
}
