package com.example.demo.Dao;

public class USE_INFO {
    private Integer useId;

    private String useName;

    private String useAge;

    private Boolean useSex;

    public Integer getUseId() {
        return useId;
    }

    public void setUseId(Integer useId) {
        this.useId = useId;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName == null ? null : useName.trim();
    }

    public String getUseAge() {
        return useAge;
    }

    public void setUseAge(String useAge) {
        this.useAge = useAge == null ? null : useAge.trim();
    }

    public Boolean getUseSex() {
        return useSex;
    }

    public void setUseSex(Boolean useSex) {
        this.useSex = useSex;
    }
}