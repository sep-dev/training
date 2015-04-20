
public class CheckStr {

	protected boolean check(String x){
		if(!x.matches(".*\".*")&&!x.matches(".*;.*")&&!x.matches(".*<.*")&&!x.matches(".*>.*")&&!x.matches(".*\'.*")&&!x.equals("")){
			return true;
		}
		else{
			return false;
		}
	}

}
