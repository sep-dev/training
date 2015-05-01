package com.addressbook.form;

import org.hibernate.validator.constraints.NotEmpty;


public class ListForm {

    @NotEmpty
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
