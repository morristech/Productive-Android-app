package co.infinum.productive.mvp.presenters;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

public interface LoginPresenter extends BasePresenter {

    void onLoginClicked(String username, String password);

    void onToggle(EditText editText, Button button, Context context);
}
