package com.jpohan.notees.contract;

public interface LoginListenerInterface {
    public void onSuccessfulLogin(String username, String password, String token);
}
