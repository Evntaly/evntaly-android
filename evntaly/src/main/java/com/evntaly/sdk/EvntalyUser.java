package com.evntaly.sdk;

import java.util.HashMap;
import java.util.Map;

public class EvntalyUser {
    public final String id;
    public final String email;
    public final String fullName;
    public final String organization;
    public final Map<String, Object> data;

    private EvntalyUser(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.fullName = builder.fullName;
        this.organization = builder.organization;
        this.data = builder.data;
    }

    public static class Builder {
        private String id;
        private String email;
        private String fullName;
        private String organization;
        private Map<String, Object> data = new HashMap<>();

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setOrganization(String organization) {
            this.organization = organization;
            return this;
        }

        public Builder setData(Map<String, Object> data) {
            this.data = data;
            return this;
        }

        public Builder addData(String key, Object value) {
            this.data.put(key, value);
            return this;
        }

        public EvntalyUser build() {
            return new EvntalyUser(this);
        }
    }

}
