package co.infinum.productive.mvp;

public interface Listener<Type> {

    void onSuccess(Type type);

    void onFailure(String message);

    //applies to the cases where internet is not accessable or not turned on
    //or for some hardware reasons connection to the internet could not be established
    void onConnectionFailure(String message);
}
