package com.music.commons.pojo.menu;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum Keys {

    KEY_ACTION("ACTION"),
    KEY_ANSWER("ANSWER");

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
