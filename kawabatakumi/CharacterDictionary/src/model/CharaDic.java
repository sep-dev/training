package model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class CharaDic
 */
@WebServlet("/CharaDic")
public class CharaDic extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */

	private int id;
	private String name;
	private String sex;
	private String height;
	private String weight;


    public CharaDic(int id,String name,String sex,String height,String weight) {
        super();
        // TODO Auto-generated constructor stub

        this.id = id;
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.weight = weight;

    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSex() { return sex; }
    public String getHeight() { return height; }
    public String getWeight() { return weight; }

}
