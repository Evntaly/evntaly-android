package com.evntaly.sdk;

import org.json.JSONObject;

public class Evnt {
    public final String title;
    public final String description;
    public final String message;
    public final JSONObject data;
    public final String type;
    public final String feature;
    public final String topic;
    public final boolean notify;
    public final boolean applyRuleOnly;

    private Evnt(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.message = builder.message;
        this.data = builder.data;
        this.type = builder.type;
        this.feature = builder.feature;
        this.topic = builder.topic;
        this.notify = builder.notify;
        this.applyRuleOnly = builder.applyRuleOnly;
    }

    public String toJson() {
        JSONObject json = new JSONObject();
        try {
            if (title != null) json.put("title", title);
            if (description != null) json.put("description", description);
            if (message != null) json.put("message", message);
            if (data != null) json.put("data", data);
            if (type != null) json.put("type", type);
            if (feature != null) json.put("feature", feature);
            if (topic != null) json.put("topic", topic);
            json.put("notify", notify);
            json.put("apply_rule_only", applyRuleOnly);
            return json.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert event to JSON", e);
        }
    }

    public static class Builder {
        private String title;
        private String description;
        private String message;
        private boolean notify = true;
        private JSONObject data = new JSONObject();
        private String type;
        private String feature;
        private String topic;
        private boolean applyRuleOnly = false;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(JSONObject data) {
            this.data = data;
            return this;
        }

        public Builder notify(boolean notify) {
            this.notify = notify;
            return this;
        }

        public Builder applyRuleOnly(boolean applyRuleOnly) {
            this.applyRuleOnly = applyRuleOnly;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder feature(String feature) {
            this.feature = feature;
            return this;
        }

        public Builder topic(String topic) {
            this.topic = topic;
            return this;
        }

        public Evnt build() {
            if (title == null || title.isEmpty()) {
                EvntalyLogger.e("Title is required and cannot be empty");
                return null;
            }
            if (description == null || description.isEmpty()) {
                EvntalyLogger.e("Description is required and cannot be empty");
                return null;
            }
            return new Evnt(this);
        }
    }
}
