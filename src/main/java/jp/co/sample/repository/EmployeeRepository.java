package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * employeeテーブルを操作するリポジトリ.
 * 
 * @author yukiando
 *
 */
@Repository
public class EmployeeRepository {

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 従業員一覧情報を入社日付順で取得する.
	 * 
	 * @return　従業員情報リスト
	 */
	public List<Employee> findAll(){
		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count"
				+ " FROM employees ORDER BY hire_date";
			return template.query(sql, EMPLOYEE_ROW_MAPPER);
	}
	
	/**
	 * 主キーから従業員情報を取得する.
	 * 
	 * @return 従業員情報
	 */
	public Employee load() {
		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count"
				+ " FROM employees WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource();
		return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
	}
	
	/**
	 * 従業員情報の扶養人数を変更する.
	 * 
	 * @param employee 従業員
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		String updateSql = "UPDATE employees SET dependents_count = :dependentsCount WHERE id = :id";
		template.update(updateSql, param);
	}
}
