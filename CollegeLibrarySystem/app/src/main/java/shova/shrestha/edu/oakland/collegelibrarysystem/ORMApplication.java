package shova.shrestha.edu.oakland.collegelibrarysystem;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

/**
 * Created by molly on 11/20/17.
 */

public class ORMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
