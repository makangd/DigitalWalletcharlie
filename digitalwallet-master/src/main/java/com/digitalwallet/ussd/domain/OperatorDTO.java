package com.digitalwallet.ussd.domain;

public class OperatorDTO {

    public OperatorDTO(Operator operator) {
        setName(operator.getName());
        setId(operator.getId());
    }

    private String name;

    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long api) {
        this.id = api;
    }
}
