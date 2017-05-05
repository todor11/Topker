package com.topker.enums;

public enum Authority {

    ADMIN ("ROLE_ADMIN", "ADMIN", "аuthority.admin"),
    COMPANY ("ROLE_COMPANY", "COMPANY", "аuthority.company"),
    USER ("ROLE_USER", "USER", "аuthority.user");

    private String fullAppText;

    private String shortAppText;

    private String userMessageObj;

    Authority(String fullAppText, String shortAppText, String userMessageObj) {
        this.fullAppText = fullAppText;
        this.shortAppText = shortAppText;
        this.userMessageObj = userMessageObj;
    }

    public String getFullAppText() {
        return this.fullAppText;
    }

    public String getShortAppText() {
        return this.shortAppText;
    }

    public String getUserMessageObj() { return this.userMessageObj; }
}
