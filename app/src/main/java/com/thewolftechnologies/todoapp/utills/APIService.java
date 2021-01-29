package com.thewolftechnologies.todoapp.utills;

import com.thewolftechnologies.todoapp.models.TodoModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    @Headers({
            "X-API-KEY:WOLF.12",
            "Accept: application/json"
    })

    //  Start to_do_list
    @GET("todo")
    Call<List<TodoModel>> getTodoList();

    @PUT("todo/{id}")
    Call<Void> putTodo(@Path("id") String id, @Body TodoModel todoModel);

    @DELETE("todo/{id}")
    Call<Void> deleteTodo(@Path("id") String id);

    @FormUrlEncoded
    @POST("todo")
    Call<Void> createTodo(
            @FieldMap Map<String, String> fields
    );
    //  End to_do_list
}
