package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者関連機能の処理の制御を行うコントローラ.
 * 
 * @author yukiando
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;
	
	/**
	 * InsertAdministratorFormをインスタンス化しそのままreturnする.
	 * 
	 * @return 管理者情報登録時に使用するフォーム
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * 「administrator/insert.html」にフォワードする.
	 * 
	 * @return 「administrator/insert.html」
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	/**
	 * 管理者情報を登録する.
	 * 
	 * @param form 管理者情報登録に
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		administratorService.insert(administrator);
		return "redirect:/";
	}
	
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
}
