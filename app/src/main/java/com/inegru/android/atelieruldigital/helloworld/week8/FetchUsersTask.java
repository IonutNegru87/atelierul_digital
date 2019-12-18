package com.inegru.android.atelieruldigital.helloworld.week8;

import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import androidx.annotation.NonNull;

/**
 * Task for fetching users in a background thread.
 *
 * After the request is made the result is sent on the UI(main) thread.
 */
public class FetchUsersTask extends AsyncTask<String, Void, FetchUsersTask.Result> {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String GET_USERS = "/users";

    private final FetchUsersCallback<List<User>> callback;


    FetchUsersTask(FetchUsersCallback<List<User>> callback) {
        this.callback = callback;
    }

    @Override
    protected Result doInBackground(String... urls) {
        Result result = null;

        URL url = null;
        try {
            url = new URL(BASE_URL + GET_USERS);
        } catch (MalformedURLException e) {
            result = new Result(e);
        }

        if (!isCancelled() && url != null) {
            List<User> users = fetchUsers(url);
            if (users != null) {
                result = new Result(users);
            } else {
                result = new Result(new IOException("No response received."));
            }
        }

        return result;
    }

    /**
     * Cancel background network operation if we do not have network connectivity.
     */
    @Override
    protected void onPreExecute() {
        if (callback != null) {
            if (!callback.isConnected()) {
                // If no connectivity, cancel task and update Callback with null data.
                callback.updateFromFailedRequest("No internet connectivity");
                cancel(true);
            }
        }
    }

    /**
     * Updates the Fetch Users task with the result.
     */
    @Override
    protected void onPostExecute(Result result) {
        if (result != null && callback != null) {
            if (result.exception != null) {
                callback.updateFromFailedRequest(result.exception.getMessage());
            } else if (result.resultValue != null) {
                callback.updateFromRequest(result.resultValue);
            }
            callback.finishFetchingUsers();
        }
    }


    private List<User> fetchUsers(URL url) {
        // For processing the input receive from the HTTP response
        InputStream stream = null;
        // Create the connection
        HttpsURLConnection connection = null;
        // Store the result list of users
        List<User> result = null;

        // Always use a try-catch as it can throw errors
        try {
            connection = (HttpsURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 5 seconds.
            connection.setReadTimeout(5000);
            // Timeout for connection.connect() arbitrarily set to 5 seconds.
            connection.setConnectTimeout(5000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("GET");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            // Open communications link (network traffic occurs here).
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream();
            if (stream != null) {
                // Converts Stream to String with max length of 500.
                result = readJsonStream(stream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Release the resources
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                // Disconnect from the HTTPS connection
                connection.disconnect();
            }
        }

        return result;
    }

    private List<User> readJsonStream(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(
            new InputStreamReader(in, StandardCharsets.UTF_8))) {
            return readMessagesArray(reader);
        }
    }

    private List<User> readMessagesArray(@NonNull JsonReader reader) throws IOException {
        List<User> users = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            users.add(readUser(reader));
        }
        reader.endArray();
        return users;
    }

    private User readUser(@NonNull JsonReader reader) throws IOException {
        Integer id = null;
        String name = null;
        String username = null;
        String email = null;
        String phone = null;
        String website = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String nexKey = reader.nextName();
            switch (nexKey) {
                case "id":
                    id = reader.nextInt();
                    break;
                case "name":
                    name = reader.nextString();
                    break;
                case "username":
                    username = reader.nextString();
                    break;
                case "email":
                    email = reader.nextString();
                    break;
                case "phone":
                    phone = reader.nextString();
                    break;
                case "website":
                    website = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setWebsite(website);

        return user;
    }

    static class Result {
        final List<User> resultValue;
        final Exception exception;

        Result(@NonNull List<User> resultValue) {
            this.resultValue = resultValue;
            this.exception = null;
        }

        Result(@NonNull Exception exception) {
            this.exception = exception;
            this.resultValue = null;
        }
    }
}
