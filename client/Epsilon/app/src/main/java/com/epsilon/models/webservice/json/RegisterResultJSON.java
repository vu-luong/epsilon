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
        int insertId;

        public int getId() {
            return insertId;
        }

        public void setId(int id) {
            this.insertId = id;
        }
    }
}
