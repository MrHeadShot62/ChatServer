package com.mrheadshot62.api.types.answer;

import com.mrheadshot62.api.types.UserDatas;

import java.io.Serializable;

/**
 * Created by novak on 13.01.2017.
 */
public class ServerAnswerAuthUserPacket implements Serializable{
    private final UserDatas userDatas;
    private final String session;

    public ServerAnswerAuthUserPacket(String session, UserDatas userDatas) {
        this.session = session;
        this.userDatas = userDatas;
    }

    public UserDatas getUserDatas() {
        return userDatas;
    }

    public String getSession() {
        return session;
    }
}
