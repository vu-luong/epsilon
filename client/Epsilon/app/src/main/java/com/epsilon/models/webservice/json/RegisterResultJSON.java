package com.epsilon.models.webservice.json;

/**
 * Created by Dandoh on 4/9/16.
 */
public class RegisterResultJSON extends GenericResultJSON{

    Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public static class Message {
        int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
