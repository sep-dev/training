package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class dao {

	private JdbcTemplate jdbcTemplate;

    public void user(JdbcTemplate jdbcTemplate){
    	final String sql = "insert into tbAddress(name, address, tel) values(?, ?, ?)";
        jdbcTemplate .update(sql, new Object[]{model.getName(),
                                    model.getAddress(),
                                    model.getTel()});
    }

    public void pass(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


}
