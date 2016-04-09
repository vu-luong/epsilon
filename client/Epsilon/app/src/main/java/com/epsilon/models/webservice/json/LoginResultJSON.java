package com.epsilon.models.webservice.json;

/**
 * Created by Dandoh on 4/9/16.
 */
public class LoginResultJSON extends GenericResultJSON {

    Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public static class Message {
        int insertId;

        public int getInsertId() {
            return insertId;
        }

        public void setInsertId(int insertId) {
            this.insertId = insertId;
        }
    }


}
