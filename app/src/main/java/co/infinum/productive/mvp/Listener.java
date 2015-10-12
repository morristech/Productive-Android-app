package co.infinum.productive.mvp;

public interface Listener<Type> {

    void onSuccess(Type type);

    void onFailure(String message);
}
