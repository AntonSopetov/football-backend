package com.juno.football.model;

public class LeadRequest {
    private String childName;      // Имя ребенка (5-12 лет)
    private int childAge;          // Возраст
    private String parentPhone;    // Телефон родителя для связи в Батуми

    // Пустой конструктор нужен Спрингу для парсинга JSON
    public LeadRequest() {}

    public LeadRequest(String childName, int childAge, String parentPhone) {
        this.childName = childName;
        this.childAge = childAge;
        this.parentPhone = parentPhone;
    }

    public String getChildName() { return childName; }
    public void setChildName(String childName) { this.childName = childName; }
    public int getChildAge() { return childAge; }
    public void setChildAge(int childAge) { this.childAge = childAge; }
    public String getParentPhone() { return parentPhone; }
    public void setParentPhone(String parentPhone) { this.parentPhone = parentPhone; }
}
