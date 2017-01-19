package com.mrheadshot62.api.types.answer;

import com.mrheadshot62.api.types.UserDatas;

import java.io.Serializable;

/**
 * Created by novak on 13.01.2017.
 */
public class ServerAnswerUserPacket implements Serializable{
    private UserDatas userDatas;

    public ServerAnswerUserPacket(UserDatas userDatas) {
        this.userDatas = userDatas;
    }

    public UserDatas getUserDatas() {
        return userDatas;
    }
}
