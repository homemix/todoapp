package com.thewolftechnologies.todoapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thewolftechnologies.todoapp.models.TodoModel;
import com.thewolftechnologies.todoapp.utills.APIService;
import com.thewolftechnologies.todoapp.utills.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoViewModel extends ViewModel {
    private MutableLiveData<List<TodoModel>> mTodoList;

    public TodoViewModel(){
        mTodoList = new MutableLiveData<>();
    }

    public MutableLiveData<List<TodoModel>> getTodoListObserver() {
        return mTodoList;
    }

    public void getTodoList(){
        APIService apiService = RetroInstance.getRetrofit().create(APIService.class);
        Call<List<TodoModel>> call = apiService.getTodoList();
        call.enqueue(new Callback<List<TodoModel>>() {
            @Override
            public void onResponse(Call<List<TodoModel>> call, Response<List<TodoModel>> response) {
                mTodoList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TodoModel>> call, Throwable t) {
                mTodoList.postValue(null);
            }
        });
    }
}
