package android.example.socialx;

import java.util.List;

public interface OnFetchDataListener <NewsApiResponse>{
    void onFetchData(List<Articles> list, String message);
    void onError(String message);
}
