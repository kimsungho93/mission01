package com.zerobase.mission01.util;

import com.google.gson.Gson;
import com.zerobase.mission01.model.dto.Result;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class MyOkHttp {
    private static final String API_KEY = PrivateInfo.API_KEY;

    private static OkHttpClient client;
    private static Request.Builder builder;

    public MyOkHttp() {
        client = new OkHttpClient.Builder()
                .build();
        builder = new Request.Builder();
    }

    public Request getRequest(int start, int end) {
        String url = "http://openapi.seoul.go.kr:8088/" + API_KEY + "/json/TbPublicWifiInfo/" + start + "/" + end + "/";

        // GET 요청 객체 생성
        return builder.url(url).get().build();
    }
    public Call getOkClient(Request request) {
        return client.newCall(request);
    }

    public int getPossibleCnt() {
        Result result = getApiResultCountBody();
        return result != null ? result.getWifiListInfo().getListTotalCount() : 0;
    }

    public Result getApiResultCountBody() {
        try (Response response = getOkClient(getRequest(1,1)).execute()){
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    return new Gson().fromJson(body.string(), Result.class);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // 비동기 통신
    public CompletableFuture<Result> getApiResult(int start, int end) {
        CompletableFuture<Result> future = new CompletableFuture<>();
        getOkClient(getRequest(start, end)).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.err.println("Error Occurred");
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResponseBody body = response.body();
                if (response.isSuccessful() && body != null) {
                    future.complete(new Gson().fromJson(body.string(), Result.class));
                }
            }
        });

        return future;
    }
}
