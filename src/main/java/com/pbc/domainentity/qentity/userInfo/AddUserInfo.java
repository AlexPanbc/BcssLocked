package com.pbc.domainentity.qentity.userInfo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by Alex on 2016/10/9.
 */
public class AddUserInfo {

    @NotBlank(message = "用户名称不能为空")
    private String name;

    @NotBlank(message = "密码不能为空")
    private String pass;

    @Size(min = 8, max = 11, message = "电话号码必须在8-11位")
    private String phone;

    @NotBlank(message = "邮箱不能为空")
    private String mailbox;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
}
