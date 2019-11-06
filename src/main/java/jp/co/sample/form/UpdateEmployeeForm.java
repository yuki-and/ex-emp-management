package jp.co.sample.form;

/**
 * 従業員情報更新時に使用するフォーム.
 * 
 * @author yukiando
 *
 */
public class UpdateEmployeeForm {

	/**	ID */
	private String id;
	
	/**	扶養人数 */
	private String dependensCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDependensCount() {
		return dependensCount;
	}

	public void setDependensCount(String dependensCount) {
		this.dependensCount = dependensCount;
	}

	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependensCount=" + dependensCount + "]";
	}
}
