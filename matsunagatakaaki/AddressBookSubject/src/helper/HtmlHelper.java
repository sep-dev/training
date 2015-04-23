package helper;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/*
 * HTMLに関する補助処理等を記述
 */

public class HtmlHelper {
    public static final String ENCORDING = "UTF-8"; //使用する文字エンコード

    //HTML文頭出力
    public static void firstHttp(PrintWriter out,String title,String css){
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ja\">");
        out.println("  <head>");
        out.println("    <meta charset=\"" + ENCORDING + "\" />");
        out.println("    <title>" + title + "</title>");
        out.println("    <script type=\"text/javascript\" src=\"./js/addressbook.js\"></script>");
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

    //HTML終了
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
        String encordStr = "";
        try {
            encordStr = new String(targetStr.getBytes("ISO8859-1"),ENCORDING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return escape(encordStr); //エスケープ処理を行ってreturn
    }

    /*
     * エスケープ処理
     * return エスケープ処理された文字列
     */
    public static String escape(String input){
        StringBuffer sb = new StringBuffer(); //エスケープ処理後の文字列格納
        for(char c : input.toCharArray()){
            switch(c){
            case '\'':
                sb.append("\'\'");
                break;
            case ';':
                break;
            case '&':
                sb.append("&amp;");
                break;
            case '<':
                sb.append("&lt;");
                break;
            case '>':
                sb.append("&gt;");
                break;
            case '"':
                sb.append("&quot;");
                break;
            case '\\':
                sb.append("&yen;");
                break;
            default:
                sb.append(c);
                break;
            }
        }
        return sb.toString();
    }
}
