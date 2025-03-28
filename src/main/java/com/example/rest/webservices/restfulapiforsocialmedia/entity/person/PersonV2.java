package com.example.rest.webservices.restfulapiforsocialmedia.entity.person;

import com.example.rest.webservices.restfulapiforsocialmedia.entity.Name;

public class PersonV2 {
    private Name name;

    public PersonV2(Name name) {
        super();
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
