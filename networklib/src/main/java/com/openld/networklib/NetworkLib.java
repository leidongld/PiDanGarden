package com.openld.networklib;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.openld.networklib.listeners.GetEnqueueListener;
import com.openld.networklib.listeners.GetExecuteListener;
import com.openld.networklib.listeners.PostEnqueueListener;
import com.openld.networklib.listeners.PostExecuteListener;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author: lllddd
 * created on: 2021/8/24 10:28
 * description:自定义封装网络库
 */
public class NetworkLib {
    // TODO: 2021/8/31  泛型的处理未完成

    /**
     * GET请求器
     */
    public static class GetRequester {
        private final OkHttpClient okHttpClient;

        private final Request.Builder requestBuilder;

        private Call call;

        private final Handler mGetSyncHandler = new Handler(Looper.getMainLooper());

        private final Handler mGetAsyncHandler = new Handler(Looper.getMainLooper());

        /**
         * 新建GetRequester实例
         *
         * @return GetRequester实例
         */
        public static GetRequester newInstance() {
            return new GetRequester();
        }

        /**
         * GET请求器
         */
        private GetRequester() {
            okHttpClient = new OkHttpClient();
            requestBuilder = new Request.Builder();
        }

        /**
         * 设置url
         *
         * @param url 请求链接
         * @return GET请求器
         */
        public GetRequester url(String url) {
            requestBuilder.url(url);
            return this;
        }

        /**
         * 设置method
         *
         * @param method      method属性
         * @param requestBody 请求体
         * @return GET请求器
         */
        public GetRequester method(String method, RequestBody requestBody) {
            requestBuilder.method(method, requestBody);
            return this;
        }

        /**
         * 设置headers
         *
         * @param name  名称
         * @param value 值
         * @return GET请求器
         */
        public GetRequester headers(String name, String value) {
            requestBuilder.header(name, value);
            return this;
        }

        /**
         * 同步请求
         *
         * @param listener 结果监听器
         */
        public <T> void execute(@NonNull GetExecuteListener<T> listener) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    call = okHttpClient.newCall(requestBuilder.build());
                    T resp;
                    try {
                        String response = Objects.requireNonNull(call.execute().body()).string();

                        Gson gson = new Gson();
                        Type type = new TypeToken<T>() {
                        }.getType();
                        try {
                            resp = gson.fromJson(response, type);
                        } catch (JsonSyntaxException e) {
                            resp = (T) response;
                        }

                        T finalResp = resp;
                        mGetSyncHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onResponse(finalResp);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        /**
         * 异步请求
         *
         * @param listener 结果监听器
         */
        public <T> void enqueue(@NonNull GetEnqueueListener<T> listener) {
            call = okHttpClient.newCall(requestBuilder.build());
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mGetAsyncHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onFailure(e.getMessage());
                                }
                            });
                        }
                    }).start();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (response.body() == null) {
                                mGetAsyncHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        listener.onFailure("返回ResponseBody为空");
                                    }
                                });
                            }
                            try {
                                String respStr = Objects.requireNonNull(response.body()).string();

                                Gson gson = new Gson();
                                Type type = new TypeToken<T>() {
                                }.getType();
                                T resp;
                                try {
                                    resp = gson.fromJson(respStr, type);
                                } catch (JsonSyntaxException e) {
                                    resp = (T) respStr;
                                }

                                T finalResp = resp;
                                mGetAsyncHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        listener.onResponse(finalResp);
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    mGetAsyncHandler.post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
            });
        }
    }

    /**
     * GET请求器
     */
    public static class PostRequester {
        private final OkHttpClient okHttpClient;

        private final Request.Builder requestBuilder;

        private Call call;

        private final Handler mPostSyncHandler = new Handler(Looper.getMainLooper());

        private final Handler mPostAsyncHandler = new Handler(Looper.getMainLooper());

        /**
         * 新建PostRequester实例
         *
         * @return PostRequester实例
         */
        public static PostRequester newInstance() {
            return new PostRequester();
        }

        /**
         * POST请求器
         */
        private PostRequester() {
            okHttpClient = new OkHttpClient();
            requestBuilder = new Request.Builder();
        }

        /**
         * 设置url
         *
         * @param url 请求链接
         * @return POST请求器
         */
        public PostRequester url(String url) {
            requestBuilder.url(url);
            return this;
        }

        /**
         * 设置method
         *
         * @param method      method属性
         * @param requestBody 请求体
         * @return POST请求器
         */
        public PostRequester method(String method, RequestBody requestBody) {
            requestBuilder.method(method, requestBody);
            return this;
        }

        /**
         * 设置headers
         *
         * @param name  名称
         * @param value 值
         * @return POST请求器
         */
        public PostRequester headers(String name, String value) {
            requestBuilder.header(name, value);
            return this;
        }

        /**
         * 同步请求
         *
         * @param listener 结果监听器
         */
        public <T> void execute(@NonNull PostExecuteListener<T> listener) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String response;
                    try {
                        call = okHttpClient.newCall(requestBuilder.build());
                        response = Objects.requireNonNull(call.execute().body()).string();

                        Gson gson = new Gson();
                        Type type = new TypeToken<T>() {
                        }.getType();
                        T resp = null;
                        try {
                            resp = gson.fromJson(response, type);
                        } catch (JsonSyntaxException e) {
                            resp = (T) response;
                        }

                        T finalResp = resp;
                        mPostSyncHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onResponse(finalResp);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        /**
         * 异步请求
         *
         * @param listener 结果监听器
         */
        public <T> void enqueue(@NonNull PostEnqueueListener<T> listener) {
            call = okHttpClient.newCall(requestBuilder.build());
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mPostAsyncHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onFailure(e.getMessage());
                                }
                            });
                        }
                    }).start();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (response.body() == null) {
                                mPostAsyncHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        listener.onFailure("返回ResponseBody为空");
                                    }
                                });
                                return;
                            }
                            try {
                                String respStr = Objects.requireNonNull(response.body()).string();

                                Gson gson = new Gson();
                                Type type = new TypeToken<T>() {
                                }.getType();
                                T resp;
                                try {
                                    resp = gson.fromJson(respStr, type);
                                } catch (JsonSyntaxException e) {
                                    resp = (T) respStr;
                                }

                                T finalResp = resp;
                                mPostAsyncHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        listener.onResponse(finalResp);
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            });
        }
    }
}
