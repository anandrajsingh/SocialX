package android.example.socialx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    SearchView searchView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching News....");
                progressDialog.show();
                Request request = new Request(MainActivity.this);
                request.getArticles(listener, "general", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching News Articles...");
        progressDialog.show();

        Request request = new Request(this);
        request.getArticles(listener, "general", null);
    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<Articles> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this, "No Data Found!!!", Toast.LENGTH_SHORT).show();
            }else {
                showNews(list);
                progressDialog.dismiss();
            }
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<Articles> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        recyclerViewAdapter = new RecyclerViewAdapter(this, list);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}