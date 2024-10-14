package com.example.webtoon;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.webtoon.Webtoon;
import java.util.List;

public class WebtoonViewModel extends AndroidViewModel {

    private MutableLiveData<List<Webtoon>> webtoonList;

    // Constructor that accepts Application context
    public WebtoonViewModel(Application application) {
        super(application);
    }

    public LiveData<List<Webtoon>> getWebtoons() {
        if (webtoonList == null) {
            webtoonList = new MutableLiveData<>();
            fetchWebtoons();
        }
        return webtoonList;
    }

    private void fetchWebtoons() {
        // Use the application context here to load the webtoons
        List<Webtoon> fetchedWebtoons = Utils.loadWebtoons(getApplication()); // Pass Application context
        webtoonList.setValue(fetchedWebtoons); // Update the LiveData
    }
}