package com.inegru.android.atelieruldigital.helloworld.week8;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.inegru.android.atelieruldigital.helloworld.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class NetworkActivityFragment extends Fragment {

    private static final String DEBUG_TAG = "Network";

    @NonNull
    private final GetDataService getDataService;
    @NonNull
    private final UsersAdapter usersAdapter = new UsersAdapter();
    @Nullable
    private AsyncTask<String, Void, FetchUsersTask.Result> getUsersTask;
    @Nullable
    private Call<List<User>> call;

    public NetworkActivityFragment() {
        // Initialize the service as soon as the fragment is created
        // If we need dynamic values we can move it in onCreate()
        getDataService = RetrofitClient.getClient()
                                       .create(GetDataService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_network, container, false);

        // Setup RecyclerView
        RecyclerView listOfUsers = root.findViewById(R.id.listOfUsers);
        listOfUsers.setHasFixedSize(true);
        listOfUsers.setAdapter(usersAdapter);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        // To avoid multiplying the list each time the fragment is started we first clear the
        // adapter
        usersAdapter.clearUsers();
        if (NetworkHelper.isConnected(requireContext())) {
            fetchUsers(GetUsersMethod.RETROFIT);
        } else {
            handleNoInternetConnection();
        }
    }

    @Override
    public void onStop() {
        cancelRetrofitRequest();
        cancelTaskRequest();
        super.onStop();
    }

    @SuppressWarnings("SameParameterValue")
    private void fetchUsers(GetUsersMethod method) {
        switch (method) {
            case RETROFIT:
                // Use retrofit for making HTTP requests
                fetchUsersRetrofit();
                break;
            case MANUAL:
                // Use native(manual) implementation for making HTTP requests
                fetchUsersManual();
                break;
        }
    }

    @SuppressWarnings("unused")
    private void fetchUsersRetrofit() {
        // Initialize the [Call<T>] for fetching users - needed to handle cancel
        call = getDataService.getAllUsers();
        // start the request by enqueueing the HTTP request
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call,
                                   @NonNull Response<List<User>> response) {
                /* SUCCESS */
                if (response.isSuccessful()) {
                    // Handle the list of users (display in RecyclerView)
                    showUsers(response.body());
                } else {
                    // Handle an response with an error (Different than failure)
                    handleResponseError(response.code(), response.message());
                }
                showFinishedTaskMessage();
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                if (call.isCanceled()) {
                    /* CANCEL */
                    handleCancelRequest();
                } else {
                    /* FAILURE */
                    handleRequestFailure(t);
                }
            }
        });
    }

    @SuppressWarnings("unused")
    private void cancelRetrofitRequest() {
        if (call != null) {
            // Cancel the request - will be handled in the onFailure() callback
            call.cancel();
            // We invalidate the call as we cannot reuse its instance (we can clone it although)
            call = null;
        }
    }

    private void fetchUsersManual() {
        getUsersTask = new FetchUsersTask(new ListFetchUsersCallback(this));
        // Execute the task for fetching users.
        getUsersTask.execute();
    }

    private void cancelTaskRequest() {
        // Cancel the task
        if (getUsersTask != null) {
            getUsersTask.cancel(true);
            // We invalidate the task as we cannot reuse its instance anymore
            getUsersTask = null;
        }
    }

    /**
     * Converts the contents of an InputStream to a String.
     */
    @SuppressWarnings({"SameParameterValue", "unused"})
    private String readStream(InputStream stream, int maxReadSize) throws IOException {
        final Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        char[] rawBuffer = new char[maxReadSize];
        int readSize;
        // If thread safety is a concern - use StringBuffer instead
        StringBuilder buffer = new StringBuilder();
        while (((readSize = reader.read(rawBuffer)) != -1) && maxReadSize > 0) {
            if (readSize > maxReadSize) {
                readSize = maxReadSize;
            }
            buffer.append(rawBuffer, 0, readSize);
            maxReadSize -= readSize;
        }
        return buffer.toString();
    }


    private void handleNoInternetConnection() {
        Log.d(DEBUG_TAG, "The device does not have an valid internet connection!");
        showSnackbarWithMessage("No internet connection!");
    }

    private void handleRequestFailure(@NonNull Throwable t) {
        Log.e(DEBUG_TAG, "onFailure: Error making http request for /users.", t);
        showSnackbarWithMessage("Cannot retrieve users!");
    }

    private void showNoUsersMessage() {
        Log.d(DEBUG_TAG, "No users available at the moment.");
        showSnackbarWithMessage("No users available!");
    }

    private void showUsers(@Nullable List<User> users) {
        if (users != null) {
            usersAdapter.addUsers(users);
        } else {
            showNoUsersMessage();
        }
    }

    private void handleResponseError(int responseCode, @Nullable String message) {
        if (message != null) {
            showSnackbarWithMessage("Error code: " + responseCode + "\n" + message);
        } else {
            showSnackbarWithMessage("Error code: " + responseCode);
        }
    }

    private void handleCancelRequest() {
        // Just log a message
        Log.d(DEBUG_TAG, "Failed to reach server for fetching users!");
    }

    private void showSnackbarWithMessage(@NonNull String message) {
        // Check if the root view is still valid as this could be called after the view was
        // destroyed and will crash the app
        if (getView() != null) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(android.R.string.ok), v -> {
                        // Do nothing - will just dismiss the Message
                    })
                    .show();
        }
    }

    private void showFinishedTaskMessage() {
        Toast.makeText(requireContext(),
                       "Finished fetching users!",
                       Toast.LENGTH_SHORT)
             .show();
    }

    private enum GetUsersMethod {
        RETROFIT,
        MANUAL
    }

    private static class ListFetchUsersCallback implements FetchUsersCallback<List<User>> {

        @NonNull
        private final WeakReference<NetworkActivityFragment> reference;

        ListFetchUsersCallback(NetworkActivityFragment fragment) {
            reference = new WeakReference<>(fragment);
        }

        @Override
        public void updateFromRequest(List<User> result) {
            if (reference.get() != null) {
                reference.get().showUsers(result);
            }
        }

        @Override
        public void updateFromFailedRequest(String message) {
            if (reference.get() != null) {
                reference.get().showSnackbarWithMessage(message);
            }
        }

        @Override
        public boolean isConnected() {
            if (reference.get() != null) {
                return NetworkHelper.isConnected(reference.get().requireContext());
            } else {
                return false;
            }
        }

        @Override
        public void finishFetchingUsers() {
            if (reference.get() != null) {
                reference.get().showFinishedTaskMessage();
            }
        }
    }
}
