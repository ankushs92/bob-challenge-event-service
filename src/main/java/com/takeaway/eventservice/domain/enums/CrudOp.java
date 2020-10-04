package com.takeaway.eventservice.domain.enums;

public enum CrudOp {

    ADD(0),
    UPDATE(1),
    DELETE(2);

    private final int code;

    CrudOp(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CrudOp from(final int code) {
        switch (code) {
            case 0 : return ADD;
            case 1 : return UPDATE;
            case 2 : return DELETE;
        }
        return null; //Expecting no other code than 0,1,2
    }


}
