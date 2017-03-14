package com.oschina.learn.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ztw on 2017/3/1.
 */

public class TokenBean extends BaseBean{

    /**
     * access_token值
     */
    @SerializedName("access_token")
    private String accessToken;

    /**
     * refresh_token值
     */
    @SerializedName("refresh_token")
    private String refreshToken;

    /**
     * 授权用户的uid
     */
    @SerializedName("uid")
    private Long uId;

    /**
     * access_token类型
     */
    @SerializedName("token_type")
    private String tokenType;

    /**
     * 超时时间(单位秒)
     */
    @SerializedName("expires_in")
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    @Override
    public String toString() {
        return "TokenBean{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", uId=" + uId +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
